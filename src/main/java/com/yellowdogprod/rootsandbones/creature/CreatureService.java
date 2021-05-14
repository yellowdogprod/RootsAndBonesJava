package com.yellowdogprod.rootsandbones.creature;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;
import com.yellowdogprod.rootsandbones.utils.ResourceUtils;

@Service
public class CreatureService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CreatureRepository creatureRepository;
	
	@Transactional
	public ResponseBean<Creature> create(CreatureRequest req, Long userId) {

		User user = userService.getUserById(userId);
		Creature creature = req.getCreature();

		if(!ResourceUtils.hasEnoughResources(user.getProfile(), req.getCost())) {
			return ResponseBean.error("Not enough resources");
		}
		
		ResourceUtils.removeResources(user.getProfile(), req.getCost());
		
		userService.updateUser(user); //rimuove le risorse all'utente
		
		creature.setProfile(user.getProfile());
		
		creatureRepository.save(creature); //salva la creatura
		
		return new ResponseBean<Creature>(creature, "Creature saved succesfully.");
		
	}
	
}
