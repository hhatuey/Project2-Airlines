package com.project.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.models.Ticket;
import com.project.models.User;
import com.project.services.UserService;
import com.project.util.UserJsonParser;


@RestController
@RequestMapping(value="/user")
public class UserController {


	private UserService uServ;
	private UserJsonParser ujp;

	@Autowired
	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
		this.ujp = UserJsonParser.getUserJsonParser();
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody User u) {
		if(uServ.createUser(u) == null) {
			return new ResponseEntity<String>("Username or email already registered to a user", HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<String>("User was created", HttpStatus.CREATED);
		}	
	}
	
	@GetMapping("/get")
	public List<User> getAllUser() {
		return uServ.getAllUser();
	}
	
	@PostMapping("/get")
	public User getUser(@RequestBody LinkedHashMap<String, Object> userJson) {
		User u = ujp.parse(userJson);
		System.out.println("Result of parsing Json User:" + u);
		return ujp.parse(userJson);
	}
	
	@GetMapping("/get/user")
	public User getUserById(@RequestParam int id) {
		return uServ.getUserById(id);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<User> signIn(@RequestBody LinkedHashMap<String, String> user) {
		User u = uServ.signIn(user.get("username"), user.get("password"));
		if(u == null) {
			return new ResponseEntity<User>(u, HttpStatus.FORBIDDEN);
		}
		else {
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
	
	@GetMapping("/role")
	public List<User> getUserByroleId(@RequestParam int id) {
		return uServ.getUserByroleId(id);
	}
	
	@GetMapping("/tickets")
	public List<Ticket> getUserTickets(@RequestParam int id) {
		return uServ.getUserTickets(id);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody User u) {
		uServ.updateUser(u);
		return new ResponseEntity<String>("Update successfully", HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody User u) {
		uServ.deleteUser(u);
		return new ResponseEntity<String>("User delete successfully", HttpStatus.OK);
	}
	
}
