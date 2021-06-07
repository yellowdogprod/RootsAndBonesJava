package com.yellowdogprod.rootsandbones.creaturerecipe;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;
import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.creature.CreatureBean;
import com.yellowdogprod.rootsandbones.creature.CreaturePartBean;
import com.yellowdogprod.rootsandbones.creature.CreatureRepository;
import com.yellowdogprod.rootsandbones.creature.CreatureRequest;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartRepository;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;
import com.yellowdogprod.rootsandbones.utils.CreatureUtils;
import com.yellowdogprod.rootsandbones.utils.ResourceUtils;

@Service
public class CreatureRecipeService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CreatureRepository creatureRepository;
	
	@Autowired
	private CreatureRecipeRepository creatureRecipeRepository;
	
	@Autowired
	private PartRepository partRepo;
	
	public ResponseBean<CreatureRecipe> createRecipe(Long userId, CreatureRecipeRequest crr){
		User user = userService.getUserById(userId);
		CreatureRecipe cr = new CreatureRecipe();
		cr.setProfile(user.getProfile());
		for(CreaturePartBean cp : crr.getParts()) {
			Optional<Part> part = partRepo.findById(cp.getPart().getId());
			if(part.isPresent()) {
				cr.addPart(part.get(), cp.getPartSlotIndex());
			}else {
				throw new IllegalStateException("A part was not found");
			}
		}
		try {
			cr = creatureRecipeRepository.save(cr); //salva la creatura			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseBean.error("Error saving a new recipe.");
		}
		return new ResponseBean<CreatureRecipe>(cr, "Creature recipe saved succesfully.");
	}

	public ResponseBean<List<CreatureRecipeBean>> getRecipes(Long userId) {
		try {
			User user = userService.getUserById(userId);
			List<CreatureRecipe> recipes = creatureRecipeRepository.getProfileRecipes(user.getProfile().getId()).get();
			return new ResponseBean<List<CreatureRecipeBean>>(CreatureUtils.toCreatureRecipeBeanList(recipes));
		}catch(Exception e) {
			return ResponseBean.error(e.getMessage());
		}
	}
	
}
