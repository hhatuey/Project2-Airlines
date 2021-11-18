package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
	public List<User> findByRoleId(int id);
	public User findById(int id);
	
}
