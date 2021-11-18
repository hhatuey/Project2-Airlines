package com.project.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import com.project.models.City;
import com.project.models.Flight;

public class FlightJsonParser {

	private static FlightJsonParser fjp;
	
	public static synchronized FlightJsonParser getFlightJsonParser() {
		if(fjp == null) {
			return new FlightJsonParser();
		}
		return fjp;
	}
	
	public Flight parse(LinkedHashMap<String, Object> flightJson) {
		try {
			int id = Integer.parseInt(flightJson.get("id").toString());
			String name = flightJson.get("name").toString();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date takeOff = dateFormat.parse(flightJson.get("takeOff").toString());
			Date eta = dateFormat.parse(flightJson.get("eta").toString());
			City destination = (City)flightJson.get("destination");
			City origin = (City)flightJson.get("origin");
			int seats = Integer.parseInt(flightJson.get("seats").toString());
			Flight flight = new Flight(id, name, takeOff, eta, destination, origin, seats);
			return flight;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
