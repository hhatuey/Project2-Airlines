package com.project.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.City;
import com.project.services.CityService;

@RestController
@RequestMapping("/city")
@CrossOrigin(origins="*")
public class CityController {
	
	private CityService cs;
	
	public CityController(CityService cs) {
		this.cs = cs;
	}
	
	@GetMapping("/get")
	public List<City> getAllCities(){
		return cs.getAllCities();
	}
}
