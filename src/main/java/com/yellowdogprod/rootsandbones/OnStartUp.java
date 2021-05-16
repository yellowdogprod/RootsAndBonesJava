package com.yellowdogprod.rootsandbones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yellowdogprod.rootsandbones.registration.RegistrationRequest;
import com.yellowdogprod.rootsandbones.registration.RegistrationService;

@Component
@Order(1)
public class OnStartUp implements CommandLineRunner{

	@Autowired
	private RegistrationService registrationService;
	
	@Override
	public void run(String... args) throws Exception {
		RegistrationRequest req = new RegistrationRequest("root", "root", "root@gmail.com");
		registrationService.register(req);
	}

}
