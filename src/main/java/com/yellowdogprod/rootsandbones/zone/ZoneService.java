package com.yellowdogprod.rootsandbones.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.beans.ResourcesBean;
import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.creature.CreaturePart;
import com.yellowdogprod.rootsandbones.creature.CreatureRepository;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartRepository;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.profile.ProfileRepository;
import com.yellowdogprod.rootsandbones.user.UserRepository;
import com.yellowdogprod.rootsandbones.utils.CreatureUtils;
import com.yellowdogprod.rootsandbones.utils.ResourceUtils;
import com.yellowdogprod.rootsandbones.utils.ZoneUtils;

@Service
public class ZoneService {

	float minX = 1.8F;
	float maxX = 6.59F;
	float minY = -2.46F;
	float maxY = 1.87F;
	
	@Autowired
	private ZoneRepository zoneRepo;

	@Autowired
	private ProfileRepository profileRepo;
	
	@Autowired
	private CreatureRepository creatureRepo;
	
	public void save(Zone zone) {

		zoneRepo.save(zone);

	}
	
	public ResponseBean<Zone> get(Long zoneId) {
		Optional<Zone> zone = zoneRepo.findById(zoneId);
		if(zone.isPresent()) {
			return new ResponseBean<Zone>(zone.get());
		}else {
			return ResponseBean.error("Zone not found.");
		}
	}

	public ResponseBean<InvasionCombatBean> getCreaturesToFight(Long zoneId) {
		InvasionCombatBean icb = new InvasionCombatBean();
		// recupero la zona tramite id
		Zone zona = zoneRepo.getOne(zoneId);

		List<Part> availableParts = zona.getParts();
		icb.setParts(availableParts);
		
		Integer level = zona.getLevel();
		Integer maxPoolSize = ZoneUtils.getMaxPoolSize(level);
		icb.setFleshRatio( (float)zona.getFlesh() / (float)maxPoolSize );
		icb.setBonesRatio( (float)zona.getBones() / (float)maxPoolSize );
		icb.setLeavesRatio( (float)zona.getLeaves() / (float)maxPoolSize );
		icb.setRootsRatio( (float)zona.getRoots() / (float)maxPoolSize );
		
		float avgRatio = (icb.getBonesRatio() + icb.getFleshRatio() + icb.getLeavesRatio() + icb.getRootsRatio() ) / 4;
		Integer additionalCreatures = Math.round(avgRatio * 3);
		icb.setNumberOfCreatures(level + additionalCreatures);
		
		return new ResponseBean<InvasionCombatBean>(icb);
	}

	public ResponseBean<EndCombatBean> fightEnded(Long userId, Long zoneId, EndCombatBean ecb) {
		EndCombatBean result = new EndCombatBean();
		result.setPlayerWon(ecb.isPlayerWon());
		ResourcesBean rb = ecb.getDroppedResources();
		rb.flesh = Math.round((float)rb.flesh * (float)Math.random());
		rb.bones = Math.round((float)rb.bones * (float)Math.random());
		rb.leaves = Math.round((float)rb.leaves * (float)Math.random());
		rb.roots = Math.round((float)rb.roots * (float)Math.random());
		result.setDroppedResources(rb);
		Profile profile = profileRepo.findByUserId(userId).get();
		Zone zone = zoneRepo.getOne(zoneId);
		if(ecb.isPlayerWon()) {
			ResourceUtils.addResources(profile, rb);
			ResourceUtils.removeResources(zone, rb);
		}else {
			ResourceUtils.addResources(zone, rb);
		}
		for(Long creatureId : ecb.getPlayerDeadCreatures()) {
			creatureRepo.deleteById(creatureId);
		}
		zoneRepo.save(zone);
		profileRepo.save(profile);
		return new ResponseBean<EndCombatBean>(result);
	}

	public ResponseBean<List<Zone>> getStartingZones(){
		return new ResponseBean<List<Zone>>(zoneRepo.getStartingZones());
	}
	
}
