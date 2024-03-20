package com.cloudbees.service;

import com.cloudbees.model.Ticket;
import com.cloudbees.model.User;

public interface TrainTicketService {

	Ticket purchaseTicket(String from, String to, User user);

	Ticket viewReceipt(String email);

	void viewSectionSeats(String section);

	void removeUser(String email);

	void modifySeat(String email, String newSection, int newSeatNumber);
}