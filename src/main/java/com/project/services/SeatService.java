package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.logging.Logging;
import com.project.models.Seat;
import com.project.repository.SeatRepo;

@Service
public class SeatService {

	private SeatRepo sDao;
	
	@Autowired
	public SeatService(SeatRepo sd) {
		this.sDao = sd;
	}

	public Seat getSeatById(int id) {
		try {
		return sDao.findById(id);
		}catch(Exception e) {
			Logging.logger.warn("Attempt to find seat with nonexistent id.");
			e.printStackTrace();
			return null;
		}
	}
	
	public Seat createSeat(Seat s) {
		try {
			sDao.save(s);
			Logging.logger.info("Successfully created seat.");
			return s;
		} catch(Exception e) {
			e.printStackTrace();
			Logging.logger.warn("Attempt to create seat failed.");
			return null;
		}
	}
	
	public List<Seat> getAllSeats(){
		try {
			return sDao.findAll();
		} catch(Exception e) {
			return new ArrayList<Seat>();
		}
	}
	
	public List<Seat> getAllSeatsByFlight(int id){
		try {
			return sDao.findByFlightId(id);
		} catch(Exception e) {
			return new ArrayList<Seat>();
		}
	}
	
	public void deleteSeat(Seat s) {
		sDao.delete(s);
		Logging.logger.info("seat deleted");
	}
	
	public Seat updateSeat(Seat s) {
		Logging.logger.info("Seat updated");
		return sDao.save(s);
	}
	
}
