package com.yellowdogprod.rootsandbones.creature;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/creatures")
@AllArgsConstructor
public class CreatureController {
	
	@Autowired
	private CreatureService creatureService;
	
	@PostMapping
	public ResponseBean<Creature> create(@RequestBody CreatureRequest req, HttpSession session){

		Long userId = (Long)session.getAttribute("userId");
		
		return creatureService.create(req, userId);
		
	}

}
