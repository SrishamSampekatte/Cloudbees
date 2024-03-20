package com.cloudbees.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudbees.model.Ticket;
import com.cloudbees.model.User;
import com.cloudbees.service.TrainTicketService;

@RestController
@RequestMapping("/ticket")
public class TrainTicketController {

	@Autowired
	private final TrainTicketService ticketService;

	@Autowired
	public TrainTicketController(TrainTicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping("/purchase")
	public Ticket purchaseTicket(@RequestParam String from, @RequestParam String to, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String email) {
		User user = new User(firstName, lastName, email);
		System.out.println("user::" + user);
		return ticketService.purchaseTicket(from, to, user);
	}

	@GetMapping("/receipt")
	public Ticket viewReceipt(@RequestParam String email) {
		return ticketService.viewReceipt(email);
	}

	@GetMapping("/seats")
	public void viewSectionSeats(@RequestParam String section) {
		ticketService.viewSectionSeats(section);
	}

	@DeleteMapping("/remove")
	public void removeUser(@RequestParam String email) {
		ticketService.removeUser(email);
	}

	@PutMapping("/modify")
	public void modifySeat(@RequestParam String email, @RequestParam String newSection,
			@RequestParam int newSeatNumber) {
		ticketService.modifySeat(email, newSection, newSeatNumber);
	}
}