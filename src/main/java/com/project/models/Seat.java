package com.project.models;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="seats")
public class Seat {
	
	@Id
	@Column(name="seat_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	//Many seats belong to one flight id's
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="flight_id", nullable=false)
	private Flight flight;
	
	@Column(name="seat_available", nullable=false)
	private Boolean seatAvailable;
		

	
	public Seat() {
		
	}
	
	public Seat(int id, Flight flight, Boolean seatAvailable) {
		super();
		this.id = id;
		this.flight = flight;
		this.seatAvailable = seatAvailable;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Flight getFlight() {
		return flight;
	}



	public void setFlight(Flight flight) {
		this.flight = flight;
	}



	public Boolean getSeatAvailable() {
		return seatAvailable;
	}



	public void setSeatAvailable(Boolean seatAvailable) {
		this.seatAvailable = seatAvailable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seat [id=");
		builder.append(id);
		builder.append(", flight=");
		builder.append(flight);
		builder.append(", seatAvailable=");
		builder.append(seatAvailable);
		builder.append("]");
		return builder.toString();
	}



}



