package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.Flight;
import com.project.models.Seat;
import com.project.services.FlightService;
import com.project.services.SeatService;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "*")
public class FlightController {
	
	private FlightService fs;
	private SeatService ss;
	
	@Autowired
	public FlightController(FlightService fs, SeatService ss) {
		this.fs = fs;
		this.ss = ss;
	} 
	
	@GetMapping(value="/get")
	public List<Flight> getAllFlights() {
		System.out.println("From get all flights");
		return fs.getAllFlight();
	}

	@GetMapping(value="/getby")
	public Flight getByFlightId(@RequestParam("id") int id) {	
		Flight f = fs.getFlightById(id);
		boolean isEmpty = ss.getAllSeatsByFlight(f.getId()).isEmpty();
		if(isEmpty == true) {
			Seat s = null;
			for (int i = 0; i < f.getSeats(); i++) {
				s = new Seat();
				s.setFlight(f);
				s.setSeatAvailable(true);
				ss.createSeat(s);
			}
		}
		return f;		
	}
	
	@GetMapping(value="/get/name")
	public Flight getByFlightId(@RequestParam("name") String name) {		
		return fs.getFlightByName(name);		
	}
	
	@GetMapping(value="/get/trip")
	public List<Flight> getByTrip(@RequestParam("orgn") String orgn, @RequestParam("dest") String dest) {
		return fs.getFlightsByTrip(orgn, dest);
	}
	
	@PostMapping(value="/create")
	public Flight createFlight(@RequestBody Flight f) {
		System.out.println("From Post Flight Controller "+f);
		return fs.createFlight(f);
	}
		
	@PostMapping("/update")
	public Flight updateFlight(@RequestBody Flight f) {
		return fs.updateFlight(f);
	}
	
	@PostMapping(value="/delete")
	public void deleteFlight(@RequestBody Flight f) {
		fs.deleteFlight(f);
	}
	

}
