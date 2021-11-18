package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.Flight;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer> {
	public Flight findByName(String name);
}
