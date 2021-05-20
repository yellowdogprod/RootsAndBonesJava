package com.yellowdogprod.rootsandbones.creature;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/creatures")
@AllArgsConstructor
public class CreatureController {
	
	@Autowired
	private CreatureService creatureService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseBean<Creature> create(@RequestBody CreatureRequest req, HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return creatureService.create(req, userId);
	}

	@GetMapping
	public ResponseBean<List<Creature>> getProfileCreatures(HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		User user = userService.getUserById(userId);
		return creatureService.getProfileCreatures(user.getProfile().getId());
	}
	
	@PutMapping
	public ResponseBean<Boolean> deployCreature(HttpSession session, @RequestBody CreatureRequest req){
		Long userId = (Long)session.getAttribute("userId");
		return creatureService.deployCreature(userId, req.getCreature().getId(), req.getPosition());
	}
	
	
}
