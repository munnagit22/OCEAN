package com.MatrimonyApp.controller;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MatrimonyApp.CustomException.UserNotFoundException;
import com.MatrimonyApp.Model.User;
import com.MatrimonyApp.repo.UserRepo;
import com.MatrimonyApp.service.UserService;
import com.MatrimonyApp.utility.AESEncryption;
import com.MatrimonyApp.utility.LoggingAspect;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class Usercontroller {
	

	   

	private static final Logger logger=LoggerFactory.getLogger(LoggingAspect.class);
	

	@Autowired
	UserService service;
	
	@Autowired
	UserRepo repo;
	
	@GetMapping("/users")
	private ResponseEntity<List<User>> getAllUser() throws Exception {
		logger.info("Customer list method started!");

		List<User> response=  service.getAllUsers();
		  return new ResponseEntity<List<User>>(response, HttpStatus.OK);

		
	}

		@PostMapping("adduser")
		public ResponseEntity<User> registerUser(@RequestBody User userRegistration) throws Exception {
		
			logger.info("Customer registration method started!");
			
			User user= service.saveUser(userRegistration);
				
				 return new   ResponseEntity<User>(user, HttpStatus.OK);
			
	}
		
		@PutMapping("subcrib")
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<User> subcribeUser(@RequestBody User userRegistration) throws Exception {
		
			logger.info("Customer subcription method started!");
	
				
			
				User obj = service.subUser(userRegistration);
				
				 return new   ResponseEntity<User>(obj, HttpStatus.OK);
				
			}
			
			
		
		
		@PutMapping("changestatus")
		public ResponseEntity<User> changestatus(@RequestBody User userRegistration) throws Exception {
		
			logger.info("Customer changestatus method started!");
	
				
			
				User user = service.changebystatus(userRegistration);
				
				 return new   ResponseEntity<User>(user, HttpStatus.OK);
				
			}
	
		
		@PostMapping("loginuser")
		public ResponseEntity<User> loginUser(@RequestBody User userRegistration) throws Exception {
		
			logger.info("Customer login method started!");
	
			String email = userRegistration.getEmail();
			String password =AESEncryption.encrypt(userRegistration.getPassword()) ;

			Optional<User> user = null;
			if (email != null && password != null) {

				user = Optional.ofNullable(repo.findByEmailAndPassword(email,password));

			
			}
			if (!user.isPresent()) {
				throw new Exception("Bad Credentials");
			}
			
			 return new ResponseEntity<User>(user.get(), HttpStatus.OK);
			
	}
		
		@GetMapping("/users/{type}")
		public ResponseEntity<List<User>> getfindByType(@PathVariable("type") String type) {
			
			logger.info("Customer list method started!");

			List<User> response = null;
			try {
				response = service.getAllbytype(type);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return new ResponseEntity<List<User>>(response, HttpStatus.OK);

		}
	
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> removeUser(@PathVariable("id") int id) throws UserNotFoundException {
			
			logger.info("User Removing method started!");

			
				String str = service.removeUserbyId(id);
		
			  return new ResponseEntity<String>(str, HttpStatus.OK);

		}
	
}
