
package com.cloudbees.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cloudbees.model.Ticket;
import com.cloudbees.service.TrainTicketServiceImpl;

public class TrainTicketControllerTest {

	private TrainTicketController controller;

	@BeforeEach
	public void setUp() {
		controller = new TrainTicketController(new TrainTicketServiceImpl());
	}

	@Test
	public void testPurchaseTicket() {
		String from = "London";
		String to = "France";
		String firstName = "Srisham";
		String lastName = "Sampekatte";
		String email = "srishamsm6@.com";

		Ticket ticket = controller.purchaseTicket(from, to, firstName, lastName, email);

		assertNotNull(ticket);
	}

	@Test
	public void testViewReceipt() {
		String email = "srishamsm6@gmail.com";

		Ticket ticket = controller.viewReceipt(email);

		assertNotNull(ticket);
		assertEquals(email, ticket.getUser().getEmail(), "Ticket should belong to the provided email");
	}

	@Test
	public void testViewSectionSeats() {
		String section = "section A ";

		controller.viewSectionSeats(section);

	}

	@Test
	public void testRemoveUser() {
		String email = "srishamsm6@gmail.com";

		controller.removeUser(email);

	}

	@Test
	public void testModifySeat() {
		String email = "srishamsm6@gmail.com";
		String newSection = "Section B";
		int newSeatNumber = 15;

		controller.modifySeat(email, newSection, newSeatNumber);

	}

}