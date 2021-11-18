package com.project.util;

import java.util.LinkedHashMap;

import com.project.models.Flight;
import com.project.models.Seat;

public class SeatJsonParser {

	private static SeatJsonParser sjp;
	
	public static synchronized SeatJsonParser getSeatJsonParser() {
		if(sjp == null) {
			return new SeatJsonParser();
		}
		return sjp;
	}
	
	public Seat parse(LinkedHashMap<String, Object> seatJson) {
		int id = Integer.parseInt((seatJson.get("id")).toString());
		FlightJsonParser fjp = FlightJsonParser.getFlightJsonParser();
		Flight flight = fjp.parse((LinkedHashMap<String, Object>)(seatJson.get("flight")));
		boolean seatAvailable = Boolean.parseBoolean((seatJson.get("seatAvailable")).toString());
		Seat seat = new Seat(id, flight, seatAvailable);
		return seat;
	}
	
}
