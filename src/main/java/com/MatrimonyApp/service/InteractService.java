package com.MatrimonyApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MatrimonyApp.Model.Interact;
import com.MatrimonyApp.Model.UserRegistration;
import com.MatrimonyApp.repo.InteractRepo;

@Service
public class InteractService {
	@Autowired
	InteractRepo repo;

	public Interact saveInteract(Interact interact) throws Exception{
		// TODO Auto-generated method stub
		return repo.save(interact);
	}

	public List<Interact> getAllInteract() throws Exception{
		
		return repo.findAll();
	}

}
