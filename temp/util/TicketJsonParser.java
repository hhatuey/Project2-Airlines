package com.project.util;

import java.util.LinkedHashMap;

import com.project.models.Flight;
import com.project.models.Seat;
import com.project.models.Ticket;
import com.project.models.User;

public class TicketJsonParser {

	private static TicketJsonParser tjp;
	
	public static synchronized TicketJsonParser geTicketJsonParser() {
		if(tjp == null) {
			return new TicketJsonParser();
		}
		return tjp;
	}
	
	public Ticket parse(LinkedHashMap<String, Object> ticketJson) {
		System.out.println(ticketJson);
		User user = UserJsonParser.getUserJsonParser().parse(
				(LinkedHashMap<String, Object>)ticketJson.get("user"));
		Seat seat = SeatJsonParser.getSeatJsonParser().parse(
				(LinkedHashMap<String, Object>)ticketJson.get("seat"));
		Flight flight = seat.getFlight();
		return new Ticket(0, user, seat, flight);
	}
	
}
