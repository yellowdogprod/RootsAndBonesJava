package com.yellowdogprod.rootsandbones.creature;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.beans.ResourcesBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipe;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipePart;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipeRepository;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipeRequest;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartRepository;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;
import com.yellowdogprod.rootsandbones.utils.CreatureUtils;
import com.yellowdogprod.rootsandbones.utils.ResourceUtils;

@Service
public class CreatureService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CreatureRepository creatureRepository;
	
	@Autowired
	private CreatureRecipeRepository creatureRecipeRepository;
	
	@Autowired
	private PartRepository partRepo;
	
	@Transactional
	public ResponseBean<CreatureBean> create(CreatureRequest req, Long userId) {

		User user = userService.getUserById(userId);
		Creature creature = new Creature();
		creature.setOnField(true);
		creature.setProfile(user.getProfile());
		creature.setX(req.getPosition().getX());
		creature.setY(req.getPosition().getY());
		
		CreatureRecipe recipe = creatureRecipeRepository.getOne(req.getRecipeId());
		
		
		ResourcesBean cost = new ResourcesBean();
		for(CreatureRecipePart cp : recipe.getParts()) {
			cost.addCost(cp.getPart());
		}
		if(!ResourceUtils.hasEnoughResources(user.getProfile(),cost)) {
			return ResponseBean.error("Not enough resources");
		}

		for(CreatureRecipePart cp : recipe.getParts()) {
			creature.addPart(cp.getPart(), cp.getId().getPartSlotIndex());
		}
		
		
		ResourceUtils.removeResources(user.getProfile(), cost);
		
		userService.updateUser(user); //rimuove le risorse all'utente
		
		creature.setProfile(user.getProfile());
		
		try {
			creatureRepository.save(creature); //salva la creatura			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseBean<CreatureBean>(CreatureUtils.toCreatureBean(creature), "Creature saved succesfully.");
		
	}
	
	public ResponseBean<List<CreatureBean>> getProfileCreatures(Long profileId){
		List<Creature> creatures = creatureRepository.getProfileCreatures(profileId).get();
		return new ResponseBean<List<CreatureBean>>(CreatureUtils.toCreatureBeanList(creatures));
	}
	
	public ResponseBean<Boolean> deployCreature(Long userId, CreatureRequest req) {
		User user = userService.getUserById(userId);
		Creature creature = creatureRepository.getOne(req.getCreature().getId());
		if(creature.getProfile().getId() != user.getProfile().getId()) {
			return ResponseBean.error("Wrong creature id.");
		}
		if(req.getDeploy()) {
			creature.setOnField(true);
			creature.setX(req.getPosition().getX());
			creature.setY(req.getPosition().getY());
		}else {
			creature.setOnField(false);
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
