package com.yellowdogprod.rootsandbones.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.creature.CreatureBean;
import com.yellowdogprod.rootsandbones.creature.CreaturePart;
import com.yellowdogprod.rootsandbones.creature.CreaturePartBean;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipe;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipeBean;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipePart;
import com.yellowdogprod.rootsandbones.creaturerecipe.CreatureRecipePartBean;
import com.yellowdogprod.rootsandbones.parts.Part;

public class CreatureUtils {

	public static CreaturePart getRandomPartOfType(List<Part> list, String type) {
		List<Part> parts = new ArrayList<Part>();
		for(Part p : list) {
			if(p.getType().equalsIgnoreCase(type)) parts.add(p);
		}
		if(parts.size() == 0) return null;
		Random rand = new Random();
		Part part = parts.get(rand.nextInt(parts.size()));
		CreaturePart cp = new CreaturePart();
		cp.setPart(part);
		return cp;
	}
	
	public static Part getPartOfType(List<Part> list, String type) {
		List<Part> parts = new ArrayList<Part>();
		for(Part p : list) {
			if(p.getType().equalsIgnoreCase(type)) parts.add(p);
		}
		if(parts.size() == 0) return null;
		Random rand = new Random();
		Part part = parts.get(rand.nextInt(parts.size()));
		return part;
	}

	public static CreatureBean toCreatureBean(Creature c) {
		CreatureBean cb = new CreatureBean();
		cb.setId(c.getId());
		cb.setOnField(c.getOnField());
		cb.setX(c.getX());
		cb.setY(c.getY());
		cb.setParts(new ArrayList<CreaturePartBean>());
		for(CreaturePart cp : c.getParts()) {
			CreaturePartBean cpb = new CreaturePartBean();
			cpb.setPart(cp.getPart());
			cpb.setPartSlotIndex(cp.getId().getPartSlotIndex());
			cb.getParts().add(cpb);
		}
		return cb;
	}
	
	public static List<CreatureBean> toCreatureBeanList(List<Creature> cl){
		ArrayList<CreatureBean> cbl = new ArrayList<CreatureBean>();
		for(Creature c : cl)
			cbl.add(toCreatureBean(c));
		return cbl;
	}
	
	
	public static CreatureRecipeBean toCreatureRecipeBean(CreatureRecipe c) {
		CreatureRecipeBean cb = new CreatureRecipeBean();
		cb.setId(c.getId());
		cb.setParts(new ArrayList<CreatureRecipePartBean>());
		for(CreatureRecipePart cp : c.getParts()) {
			CreatureRecipePartBean cpb = new CreatureRecipePartBean();
			cpb.setPart(cp.getPart());
			cpb.setPartSlotIndex(cp.getId().getPartSlotIndex());
			cb.getParts().add(cpb);
		}
		return cb;
	}
	
	public static List<CreatureRecipeBean> toCreatureRecipeBeanList(List<CreatureRecipe> cl){
		ArrayList<CreatureRecipeBean> cbl = new ArrayList<CreatureRecipeBean>();
		for(CreatureRecipe c : cl)
			cbl.add(toCreatureRecipeBean(c));
		return cbl;
	}
	
}
