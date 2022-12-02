package com.MatrimonyApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MatrimonyApp.Model.Interact;

@Repository
public interface InteractRepo extends JpaRepository<Interact, Integer>{

	Interact findBySenderAndReceiver(String send, String rec);

}
