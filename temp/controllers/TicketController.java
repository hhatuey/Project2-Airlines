package com.project.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.Flight;
import com.project.models.Ticket;
import com.project.models.User;
import com.project.services.TicketService;
import com.project.util.FlightJsonParser;
import com.project.util.TicketJsonParser;
import com.project.util.UserJsonParser;

@RestController
@RequestMapping(value="/tickets")
public class TicketController {

	private UserJsonParser ujp;
	private FlightJsonParser fjp;
	private TicketJsonParser tjp;
	private TicketService tService;

	@Autowired
	public TicketController(TicketService tService) {
		super();
		this.tService = tService;
		this.tjp = TicketJsonParser.geTicketJsonParser();
		this.fjp = FlightJsonParser.getFlightJsonParser();
		this.ujp = UserJsonParser.getUserJsonParser();
	}
	
	@PostMapping(value="/create")
	public Ticket createTicket(@RequestBody LinkedHashMap<String, Object> ticketJson) {
		System.out.println(ticketJson);
		Ticket t = tjp.parse(ticketJson);
		System.out.println(t);
		return tService.createTicket(t);
	}
	
	@GetMapping(value="/")
	public List<Ticket> getAllTickets() {
		System.out.println("In Get All Tickets");
		return tService.getAllTickets();
	}
	
	@PostMapping(value="/delete")
	public Ticket deleteTicket(LinkedHashMap<String, Object> ticketJson) {
		System.out.println(ticketJson);
		Ticket t = tjp.parse(ticketJson);
		if(tService.deleteTicket(t))
			return t;
		
		return null;
	}
	
	@PostMapping(value="/get/user")
	public List<Ticket> getTicketsByUser(@RequestBody LinkedHashMap<String, Object> userJson) {
		User user = ujp.parse(userJson);
		return tService.getTicketsByUser(user);
	}
	
	@PostMapping(value="/get/flight")
	public List<Ticket> getTicketsByFlight(@RequestBody LinkedHashMap<String, Object> flightJson) {
		Flight flight = fjp.parse(flightJson);
		for (Ticket tk : tService.getTicketsByFlight(flight))
			System.out.println(tk);
		return tService.getTicketsByFlight(flight);
	}
	
	/*  NOT WORKING WILL FIX LATER
	 *	@PostMapping(value="/update")
	 *	public Ticket updateTicket(@RequestBody LinkedHashMap<String, Object> ticketJson) {
	 *		Ticket t = tjp.parse(ticketJson);
	 *		return tService.updateTicket(t);
	 *	}
	 */
}
