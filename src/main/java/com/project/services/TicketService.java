package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.logging.Logging;
import com.project.models.Flight;
import com.project.models.Ticket;
import com.project.models.User;
import com.project.repository.TicketRepo;

@Service
@Transactional
public class TicketService {

	private TicketRepo tDao;
	private FlightService fServ;
	
	@Autowired
	public TicketService(TicketRepo tDao, FlightService fServ) {
		this.tDao = tDao;
		this.fServ = fServ;
	}
	
	
	public Ticket createTicket(Ticket t) {
		System.out.println(t);
		try {
			tDao.save(t);
			Logging.logger.warn("A Ticket was created.");
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		return t;
	}
	
	public Ticket updateTicket(Ticket t) {
		try {
			tDao.save(t);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteTicket(Ticket t) {
		try {
			tDao.delete(t);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Ticket> getAllTickets() {
		try {
			List<Ticket> tickets = tDao.findAll();
			return tickets;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Ticket> getTicketsByUser(User user) {
		try {
			List<Ticket> userTickets = tDao.findAllByUser(user);
			return userTickets;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Ticket> getTicketsByFlight(int flightId) {
		try {
			Flight flight = fServ.getFlightById(flightId);
			List<Ticket> tickets = tDao.findAllByFlight(flight);
			return tickets;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Ticket getTicketById(int id) {
		try {
			Ticket t = tDao.findByTicketId(id);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
