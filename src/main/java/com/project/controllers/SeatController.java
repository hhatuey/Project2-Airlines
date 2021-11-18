package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.Seat;
import com.project.services.SeatService;


@RestController
@RequestMapping(value="/seat")
@CrossOrigin(origins = "*")

public class SeatController {

	private SeatService sServ;
	@Autowired 
	
	public SeatController(SeatService s) {
		this.sServ = s;
	}

	@GetMapping(value="/get")
	public List<Seat> getAll(){
		System.out.println("In the /get method");
		return sServ.getAllSeats();
	}
	
	@GetMapping (value="/get/flight/seats")
	public List<Seat> getSeatsByFlight(@RequestParam int id){
		System.out.println("In the /get/flight/seats method");
		return sServ.getAllSeatsByFlight(id);
	}
	
	@PostMapping(value="/create")
	public Seat createSeat(@RequestBody Seat s) {
		System.out.println("In the /create method");
		System.out.println(s);
		return sServ.createSeat(s);
	}
	
	
	@GetMapping(value="/get/seat")
	public Seat getUserById(@RequestParam int id) {
		System.out.println("In the /get/seat method");
		return sServ.getSeatById(id);
	}
	
	@PostMapping(value="/update")
	public Seat updateSeat(@RequestBody Seat s) {
		s.setSeatAvailable(false);
		System.out.println("In the /update method");
		System.out.println(s);
		return sServ.updateSeat(s);
	}
	
	
	
	
}
