package com.MatrimonyApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MatrimonyApp.Model.User;


public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmail(String tempEmailId);

	User findByEmailAndPassword(String email, String password);

	List<User> findByBtype(String type);

	User getByEmail(String email);

}
