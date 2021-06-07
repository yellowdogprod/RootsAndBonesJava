package com.yellowdogprod.rootsandbones.utils;

import com.yellowdogprod.rootsandbones.beans.ResourcesBean;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.zone.Zone;

public class ResourceUtils {

	public static boolean hasEnoughResources(Profile p, ResourcesBean r) {
		if(r.getFlesh() > p.getFlesh()) return false;
		if(r.getBones() > p.getBones()) return false;
		if(r.getLeaves() > p.getLeaves()) return false;
		if(r.getRoots() > p.getRoots()) return false;
		return true;
	}
	
	public static void removeResources(Profile p, ResourcesBean r) {
		p.setFlesh(p.getFlesh() - r.getFlesh());
		p.setBones(p.getBones() - r.getBones());
		p.setLeaves(p.getLeaves() - r.getLeaves());
		p.setRoots(p.getRoots() - r.getRoots());
	}
	
	public static void removeResources(Zone p, ResourcesBean r) {
		p.setFlesh(p.getFlesh() - r.getFlesh());
		p.setBones(p.getBones() - r.getBones());
		p.setLeaves(p.getLeaves() - r.getLeaves());
		p.setRoots(p.getRoots() - r.getRoots());
	}
	
	public static void addResources(Profile p, ResourcesBean r) {
		p.setFlesh(p.getFlesh() + r.getFlesh());
		p.setBones(p.getBones() + r.getBones());
		p.setLeaves(p.getLeaves() + r.getLeaves());
		p.setRoots(p.getRoots() + r.getRoots());
	}
	
	public static void addResources(Zone p, ResourcesBean r) {
		p.setFlesh(p.getFlesh() + r.getFlesh());
		p.setBones(p.getBones() + r.getBones());
		p.setLeaves(p.getLeaves() + r.getLeaves());
		p.setRoots(p.getRoots() + r.getRoots());
	}
	
}
