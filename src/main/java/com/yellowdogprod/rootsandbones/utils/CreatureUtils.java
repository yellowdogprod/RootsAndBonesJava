package com.yellowdogprod.rootsandbones.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yellowdogprod.rootsandbones.parts.Part;

public class CreatureUtils {

	public static Part getRandomPartOfType(List<Part> list, String type) {
		List<Part> parts = new ArrayList<Part>();
		for(Part p : list) {
			if(p.getType().equalsIgnoreCase(type)) parts.add(p);
		}
		if(parts.size() == 0) return null;
		Random rand = new Random();
		return parts.get(rand.nextInt(parts.size()));
	}
	
}
