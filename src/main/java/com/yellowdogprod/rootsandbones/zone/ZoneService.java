package com.yellowdogprod.rootsandbones.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartRepository;
import com.yellowdogprod.rootsandbones.utils.CreatureUtils;

@Service
public class ZoneService {

	float minX = 1.8F;
	float maxX = 6.59F;
	float minY = -2.46F;
	float maxY = 1.87F;
	
	@Autowired
	private ZoneRepository zoneRepo;

	public void save(Zone zone) {

		zoneRepo.save(zone);

	}

	public ResponseBean<List<Creature>> getCreaturesToFight(Long zoneId) {
		List<Creature> creatures = new ArrayList<Creature>();
		// recupero la zona tramite id
		Zone zona = zoneRepo.getOne(zoneId);

		List<Part> availableParts = zona.getParts();
		int randomNum = ThreadLocalRandom.current().nextInt(2, 5 + 1);
		for (int x = 0; x < randomNum; x++) {
			Creature creature = new Creature();
			creature.setParts(new ArrayList<Part>());
			
			creature.getParts().add(CreatureUtils.getRandomPartOfType(availableParts, "BODY"));
			creature.getParts().add(CreatureUtils.getRandomPartOfType(availableParts, "ARMS"));
			creature.getParts().add(CreatureUtils.getRandomPartOfType(availableParts, "HEAD"));
			creature.getParts().add(CreatureUtils.getRandomPartOfType(availableParts, "LEGS"));
			
		    float X = minX + new Random().nextFloat() * (maxX - minX);
			
			creature.setX(X);

		    float Y = minY + new Random().nextFloat() * (maxY - minY);
			
			creature.setY(Y);
			
			creatures.add(creature);

		}
		return new ResponseBean<List<Creature>>(creatures);
	}

}
