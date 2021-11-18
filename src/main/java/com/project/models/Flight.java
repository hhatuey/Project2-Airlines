package com.project.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="flight")
public class Flight {

	@Id
	@Column(name="flight_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="takeoff")
	private Date takeoff;
	
	@Column(name="eta")
	private Date eta;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="destination_id")
//	@JsonIgnore
	private City destination;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="origin_id")
//	@JsonIgnore
	private City origin;
	
	@Column(name="seats")
	private int seats;
	
	public Flight() {
		
	}

	public Flight(int id, String name, Date takeoff, Date eta, City destination, City origin, int seats) {
		super();
		this.id = id;
		this.name = name;
		this.takeoff = takeoff;
		this.eta = eta;
		this.destination = destination;
		this.origin = origin;
		this.seats = seats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTakeoff() {
		return takeoff;
	}

	public void setTakeoff(Date takeoff) {
		this.takeoff = takeoff;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}

	public City getOrigin() {
		return origin;
	}

	public void setOrigin(City origin) {
		this.origin = origin;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Flight [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", takeoff=");
		builder.append(takeoff);
		builder.append(", eta=");
		builder.append(eta);
		builder.append(", destination=");
		builder.append(destination);
		builder.append(", origin=");
		builder.append(origin);
		builder.append(", seats=");
		builder.append(seats);
		builder.append("]");
		return builder.toString();
	}


	
}
