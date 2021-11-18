package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.Flight;
import com.project.models.Seat;
import com.project.repository.CityRepo;
import com.project.repository.FlightRepo;
import com.project.repository.SeatRepo;

@Service
public class FlightService {

	private FlightRepo fr;
	private SeatRepo sr;
	private CityRepo cr;

	@Autowired
	public FlightService(FlightRepo fr, SeatRepo sr, CityRepo cr) {
		this.fr = fr;
		this.sr = sr;
		this.cr = cr;
	}

	public Flight getFlightById(int id) {
		return fr.findById(id).get();
	}

	public Flight getFlightByName(String name) {
		return fr.findByName(name);
	}
	
	public List<Flight> getFlightsByTrip(String orgn, String dest) {
		int orgnid = cr.findByName(orgn).getId();
		int destid = cr.findByName(dest).getId();
		return fr.findByOriginIdAndDestinationId(orgnid, destid);
	}

	public List<Flight> getAllFlight() {
		return fr.findAll();
	}

	public Flight createFlight(Flight f) {
		Flight nf = fr.save(f);
		Seat s = null;
		for (int i = 0; i < nf.getSeats(); i++) {
			s = new Seat();
			s.setFlight(nf);
			s.setSeatAvailable(true);
			sr.save(s);
		}
		return nf;
	}

	public void populateSeats(Flight f) {
		if (sr.findByFlightId(f.getId()).isEmpty()) {
			Seat s = null;
			for (int i = 0; i < f.getSeats(); i++) {
				s = new Seat();
				s.setFlight(f);
				s.setSeatAvailable(true);
				sr.save(s);
			}
		}		
	}

	public Flight updateFlight(Flight f) {
		Flight nf = fr.getById(f.getId());
		nf.setName(f.getName());
		nf.setTakeoff(f.getTakeoff());
		nf.setEta(f.getEta());
		nf.setDestination(f.getDestination());
		nf.setOrigin(f.getOrigin());
		nf.setSeats(f.getSeats());
		return fr.save(nf);
	}

	public void deleteFlight(Flight f) {
		fr.delete(f);
	}

}
