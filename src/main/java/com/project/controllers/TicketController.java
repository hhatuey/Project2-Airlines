package com.project.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.Flight;
import com.project.models.Ticket;
import com.project.models.User;
import com.project.services.FlightService;
import com.project.services.TicketService;
import com.project.util.FlightJsonParser;
import com.project.util.TicketJsonParser;
import com.project.util.UserJsonParser;

@RestController
@RequestMapping(value="/tickets")
@CrossOrigin(origins = "*")
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
//		return t;
		return tService.createTicket(t);
	}
	
//	@PostMapping(value="/create")
//	public Ticket createTicket(@RequestBody Ticket t) {
//		System.out.println("From /create method");
//		System.out.println(t);
////		return t;
//		return tService.createTicket(t);
//	}
	
	@GetMapping(value="/")
	public List<Ticket> getAllTickets() {
		System.out.println("In Get All Tickets");
		return tService.getAllTickets();
	}
	
	@GetMapping(value="/id")
	public Ticket getTicketById(@RequestParam("id") int id) {
		return tService.getTicketById(id);
	}
	
	@DeleteMapping(value="/id")
	public Ticket deleteTicket(@RequestParam("id") int id) {
		Ticket t = tService.getTicketById(id);
		if(tService.deleteTicket(t))
			return t;
		
		return null;
	}
	
	@GetMapping(value="/flight/id")
	public List<Ticket> getTicketsByFlight(@RequestParam("id") int flightId) {
		for (Ticket tk : tService.getTicketsByFlight(flightId))
			System.out.println(tk);
		return tService.getTicketsByFlight(flightId);
	}
	
	/*  NOT WORKING WILL FIX LATER
	 *	@PostMapping(value="/update")
	 *	public Ticket updateTicket(@RequestBody LinkedHashMap<String, Object> ticketJson) {
	 *		Ticket t = tjp.parse(ticketJson);
	 *		return tService.updateTicket(t);
	 *	}
	 */
}
