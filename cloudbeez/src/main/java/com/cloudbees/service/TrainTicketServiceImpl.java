package com.cloudbees.service;

import java.util.HashMap;
import java.util.Map;

import com.cloudbees.model.Ticket;
import com.cloudbees.model.User;

public class TrainTicketServiceImpl implements TrainTicketService {
	private Map<User, Ticket> ticketMap = new HashMap<>();
	private Map<String, Map<Integer, User>> sectionSeats = new HashMap<>();

	public TrainTicketServiceImpl() {
		sectionSeats.put("section A ", new HashMap<>());
		sectionSeats.put("section B", new HashMap<>());
	}

	@Override
	public Ticket purchaseTicket(String from, String to, User user) {
		String section = assignSection();
		int seatNumber = assignSeatNumber(section);

		Ticket ticket = new Ticket(from, to, user, 20.0, section, seatNumber);

		ticketMap.put(user, ticket);
		sectionSeats.get(section).put(seatNumber, user);

		return ticket;
	}

	@Override
	public Ticket viewReceipt(String email) {
		for (User user : ticketMap.keySet()) {
			if (user.getEmail().equals(email)) {
				return ticketMap.get(user);
			}
		}
		return null;
	}

	@Override
	public void viewSectionSeats(String section) {
		System.out.println("Users and their seats in section " + section + ":");
		if (sectionSeats.containsKey(section)) {
			for (Map.Entry<Integer, User> entry : sectionSeats.get(section).entrySet()) {
				System.out.println("Seat Number: " + entry.getKey() + ", User: " + entry.getValue().getFirstName() + " "
						+ entry.getValue().getLastName());
			}
		} else {
			System.out.println("Section " + section + " does not exist.");
		}
	}

	@Override
	public void removeUser(String email) {
		for (User user : ticketMap.keySet()) {
			if (user.getEmail().equals(email)) {
				Ticket ticket = ticketMap.get(user);
				sectionSeats.get(ticket.getSection()).remove(ticket.getSeatNumber());
				ticketMap.remove(user);
				break;
			}
		}
	}

	@Override
	public void modifySeat(String email, String newSection, int newSeatNumber) {
		User user = null;
		Ticket ticket = null;

		for (Map.Entry<User, Ticket> entry : ticketMap.entrySet()) {
			if (entry.getKey().getEmail().equals(email)) {
				user = entry.getKey();
				ticket = entry.getValue();
				break;
			}
		}

		if (user != null && ticket != null) {
			sectionSeats.get(ticket.getSection()).remove(ticket.getSeatNumber());
			ticket.setSection(newSection);
			ticket.setSeatNumber(newSeatNumber);
			sectionSeats.get(newSection).put(newSeatNumber, user);
		}
	}

	private String assignSection() {
		return ticketMap.size() % 2 == 0 ? "section A " : "section B";
	}

	private int assignSeatNumber(String section) {
		return sectionSeats.get(section).size() + 1;
	}
}