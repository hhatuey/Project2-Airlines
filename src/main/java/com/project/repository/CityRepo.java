package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.models.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	public City findByName(String name);
}
