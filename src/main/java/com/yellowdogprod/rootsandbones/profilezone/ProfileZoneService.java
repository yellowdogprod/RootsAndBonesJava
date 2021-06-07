package com.yellowdogprod.rootsandbones.profilezone;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.creature.CreatureRepository;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.profile.ProfileRepository;
import com.yellowdogprod.rootsandbones.utils.ZoneUtils;
import com.yellowdogprod.rootsandbones.zone.Zone;
import com.yellowdogprod.rootsandbones.zone.ZoneRepository;

@Service
public class ProfileZoneService {
	
	@Autowired
	private ZoneRepository zoneRepo;

	@Autowired
	private ProfileRepository profileRepo;
	
	@Autowired
	private ProfileZoneRepository profileZoneRepo;

	public ResponseBean<Boolean> checkStartingZone(Long userId) {
		try {
			Profile profile = profileRepo.findByUserId(userId).get();		
			List<ProfileZone> zones = profileZoneRepo.getProfileZonesForProfile(profile.getId());
			return new ResponseBean<Boolean>(zones.size() > 0);
		}catch (NoSuchElementException e) {
			return ResponseBean.error("Profile not found");
		}
	}

	public ResponseBean<Boolean> selectStartingZone(Long userId, Long zoneId) {
		try {
			
			Boolean hasAlreadySelectedAZone = checkStartingZone(userId).getResult();
			
			if(hasAlreadySelectedAZone) {
				return ResponseBean.error("Profile already has a starting zone.");
			}
			
			Profile profile = profileRepo.findByUserId(userId).get();		
			Zone zone = zoneRepo.getOne(zoneId);
			ProfileZone pz = new ProfileZone();
			pz.setProfile(profile);
			pz.setZone(zone);
			profileZoneRepo.save(pz);
			return new ResponseBean<Boolean>(true);
		}catch (NoSuchElementException e) {
			return ResponseBean.error("Profile not found");
		}
	}

	public ResponseBean<List<ProfileZoneBean>> getInfestedZones(Long userId) {
		try {
			ResponseBean<List<ProfileZoneBean>> res = new ResponseBean<List<ProfileZoneBean>>();
			res.setResult(new ArrayList<ProfileZoneBean>());
			Profile profile = profileRepo.findByUserId(userId).get();		
			List<ProfileZone> zones = profileZoneRepo.getProfileZonesForProfile(profile.getId());
			for(ProfileZone pz : zones) {
				ProfileZoneBean pzb = new ProfileZoneBean();
				pzb.setId(pz.getId());
				pzb.setZone(ZoneUtils.toBean(pz.getZone()));
				res.getResult().add(pzb);
			}
			res.setCode(200);
			return res;
		}catch (NoSuchElementException e) {
			return ResponseBean.error("Profile not found");
		}
	}

	
	
}
