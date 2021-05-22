package com.yellowdogprod.rootsandbones.zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneService {

	@Autowired
	private ZoneRepository zoneRepo;
	
	public void save(Zone zone) {

		zoneRepo.save(zone);
		
	}

	
	
}
