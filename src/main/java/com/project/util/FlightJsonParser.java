package com.project.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.models.City;
import com.project.models.Flight;

public class FlightJsonParser {

	private static FlightJsonParser fjp;

	public static synchronized FlightJsonParser getFlightJsonParser() {
		if (fjp == null) {
			return new FlightJsonParser();
		}
		return fjp;
	}

	public Flight parse(LinkedHashMap<String, Object> flightJson) {
		Flight flight = null;
		try {
			int id = Integer.parseInt(flightJson.get("id").toString());
			String name = flightJson.get("name").toString();
//			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date takeOff=Date.valueOf(flightJson.get("takeoff").toString());
//			Date eta = Date.valueOf(flightJson.get("eta").toString());
//			Date eta = dateFormat.parse(flightJson.get("eta").toString());
//			Date takeOff = dateFormat.parse(flightJson.get("takeoff").toString());

			ZonedDateTime takeOff = ZonedDateTime.parse(flightJson.get("takeoff").toString());
			ZonedDateTime eta = ZonedDateTime.parse(flightJson.get("eta").toString());

			ObjectMapper om = new ObjectMapper();
			
			String json = om.writeValueAsString(flightJson.get("destination"));
			City destination = om.readValue(json, City.class);			

			json = om.writeValueAsString(flightJson.get("origin"));
			City origin = om.readValue(json, City.class);
			
			int seats = Integer.parseInt(flightJson.get("seats").toString());
			
			flight = new Flight(id, name, Date.from(takeOff.toInstant()), Date.from(eta.toInstant()),
					destination, origin, seats);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flight;
	}

}
