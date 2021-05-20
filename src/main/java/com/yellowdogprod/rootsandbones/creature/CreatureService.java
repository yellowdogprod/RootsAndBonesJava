package com.yellowdogprod.rootsandbones.creature;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;
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
	
	public ResponseBean<List<Creature>> getProfileCreatures(Long profileId){
		List<Creature> creatures = creatureRepository.getProfileCreatures(profileId).get();
		return new ResponseBean<List<Creature>>(creatures);
	}
	
	public ResponseBean<Boolean> deployCreature(Long userId, Long creatureId, VectorBean pos) {
		User user = userService.getUserById(userId);
		Creature creature = creatureRepository.getOne(creatureId);
		if(creature.getProfile().getId() != user.getProfile().getId()) {
			return ResponseBean.error("Wrong creature id.");
		}
		creature.setOnField(true);
		if(pos != null) {
			creature.setX(pos.getX());
			creature.setY(pos.getY());
		}
		creatureRepository.save(creature);
		return new ResponseBean<Boolean>(true);
	}

	public ResponseBean<Boolean> moveCreature(Long userId, Long creatureId, VectorBean pos) {
		User user = userService.getUserById(userId);
		Creature creature = creatureRepository.getOne(creatureId);
		if(creature.getProfile().getId() != user.getProfile().getId()) {
			return ResponseBean.error("Wrong creature id.");
		}
		creature.setX(pos.getX());
		creature.setY(pos.getY());
		creatureRepository.save(creature);
		return new ResponseBean<Boolean>(true);
	}
	
}
