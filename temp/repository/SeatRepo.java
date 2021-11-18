package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.models.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer>{

	Seat findById(int id);
	List<Seat> findAll();
	
//	@Query("select * from seats s where s.flight_id = :id")
//	List<Seat> findByFlight(@Param("id") int id);
	
}