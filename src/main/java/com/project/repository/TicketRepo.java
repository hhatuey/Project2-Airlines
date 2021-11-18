package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.models.Flight;
import com.project.models.Ticket;
import com.project.models.User;

public interface TicketRepo extends JpaRepository<Ticket, Integer>{

	//TODO Add Custom Methods
	public List<Ticket> findAllByUser(User user);
	public Ticket findByTicketId(int ticketId);
	public List<Ticket> findAllByFlight(Flight flight);
	
}
