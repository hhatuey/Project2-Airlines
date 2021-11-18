package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.models.City;
import com.project.repository.CityRepo;

@Service
public class CityService {

	private CityRepo cr;
	
	@Autowired
	public CityService(CityRepo cr) {
		this.cr = cr;
	}
	
	public List<City> getAllCities() {
		return cr.findAll();
	}
}
