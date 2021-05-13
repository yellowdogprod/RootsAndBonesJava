package com.yellowdogprod.rootsandbones.email;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {

	@Async
	void send(String to, String email);
	
}
