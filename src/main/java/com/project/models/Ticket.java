package com.project.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ticket")
public class Ticket {

	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketId;
	
	@ManyToOne(cascade=CascadeType.MERGE)//, fetch=FetchType.LAZY
	@JoinColumn(name="user_id")
//	@JsonIgnore
	private User user;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="seat_id")
//	@JsonIgnore
	private Seat seat;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="flight_id")
//	@JsonIgnore
	private Flight flight;
	
	public Ticket() {
		this.ticketId = 0;
	}

	public Ticket(int ticketId, User user, Seat seat, Flight flight) {
		super();
		this.ticketId = ticketId;
		this.user = user;
		this.seat = seat;
		this.flight = flight;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [ticketId=");
		builder.append(ticketId);
		builder.append(", user=");
		builder.append(user);
		builder.append(", seat=");
		builder.append(seat);
		builder.append(", flight=");
		builder.append(flight);
		builder.append("]");
		return builder.toString();
	}

	
}
