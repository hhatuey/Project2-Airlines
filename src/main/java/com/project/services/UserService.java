package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.logging.Logging;
import com.project.models.Ticket;
import com.project.models.User;
import com.project.repository.UserRepo;

@Service
public class UserService {
	
	private UserRepo uDao;
	
	@Autowired
	public UserService(UserRepo u) {
		this.uDao = u;
	}
	
	public User createUser(User u) {
		try {
			uDao.save(u);

			Logging.logger.warn("A user was created.");
			return u;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUser() {
		try {
			return uDao.findAll();
		} catch(Exception e) {
			return new ArrayList<User>();
		}
	}
	
	public User getUserById(int id) {
		return uDao.findById(id);
	}
	
	public User signIn(String username, String password) {
		User u = uDao.findByUsername(username);
		
		if(u == null) {
			return null;
		}
		else {
			if(!u.getPassword().equals(password)) {
				return null;
			}
			else {

				Logging.logger.warn("A user logged in");
				return u;
			}
		}
		
	}
	
	public List<User> getUserByroleId(int id) {
		return uDao.findByRoleId(id);
	}
	
	public List<Ticket> getUserTickets(int id) {
		User u = uDao.findById(id);
		return u.getTickets();
	}
	
	public User displayUser(String username) {
		User u = uDao.findByUsername(username);
		if(u == null) {
			return null;
		}
		else {
			return u;
		}
	}
	
	public User updateUser(User u) {
		User u1 = uDao.findById(u.getUserId());
		u1.setFirstName(u.getFirstName());
		u1.setLastName(u.getLastName());
		u1.setPassword(u.getPassword());
		u1.setEmail(u.getEmail());
		
		uDao.save(u1);
		return u1;
	}
	
	public void deleteUser(User u) {
		uDao.delete(u);
	}
	
}
