package com.yellowdogprod.rootsandbones.utils;

import com.yellowdogprod.rootsandbones.zone.Zone;
import com.yellowdogprod.rootsandbones.zone.ZoneBean;

public class ZoneUtils {

	private static Integer poolBase = 250;
	
	public static Integer getMaxPoolSize(Integer level) {
		Integer pool = poolBase;
		for (int i = 0; i < level-1; i++) {
			pool = pool*2;
		}		
		return pool;
	}
	
	public static ZoneBean toBean(Zone z) {
		ZoneBean zb = new ZoneBean();
		zb.setId(z.getId());
		zb.setBones(z.getBones());
		zb.setFlesh(z.getFlesh());
		zb.setLeaves(z.getLeaves());
		zb.setLevel(z.getLevel());
		zb.setName(z.getName());
		zb.setRoots(z.getRoots());
		return zb;
	}
	
}
