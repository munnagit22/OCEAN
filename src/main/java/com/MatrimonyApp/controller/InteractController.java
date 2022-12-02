package com.MatrimonyApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MatrimonyApp.Model.Interact;
import com.MatrimonyApp.Model.UserRegistration;
import com.MatrimonyApp.repo.InteractRepo;
import com.MatrimonyApp.service.InteractService;
import com.MatrimonyApp.utility.LoggingAspect;

@RestController
public class InteractController {
	

	private static final Logger logger=LoggerFactory.getLogger(LoggingAspect.class);
	
	@Autowired
	InteractService service;
	
	@Autowired
	InteractRepo repo;
	
	@GetMapping("/interactlist")
	@CrossOrigin(origins = "http://localhost:4200")
	private ResponseEntity<List<Interact>> getAllUser() throws Exception {
		logger.info("Customer list method started!");

		List<Interact> response=  service.getAllInteract();
		  return new ResponseEntity<List<Interact>>(response, HttpStatus.OK);

		
	}
	
	@PostMapping("sendrequest")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Interact> registerUser(@RequestBody Interact interact) throws Exception {
	
		logger.info("send request method started!");
		String send = interact.getSender();
		String rec = interact.getReceiver();
		Interact user = repo.findBySenderAndReceiver(send , rec);
			if (user != null) {
				throw new Exception("user with " + user + " already requested");
			}
		
		
			Interact obj = null;
			obj = service.saveInteract(interact);
			
			 return new   ResponseEntity<Interact>(obj, HttpStatus.OK);
		
}

}
