package com.yellowdogprod.rootsandbones.profile;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.parts.PartRepository;
import com.yellowdogprod.rootsandbones.parts.PartService;
import com.yellowdogprod.rootsandbones.user.User;

@Service
public class ProfileService {

	private final String[] initialParts = new String[] {"arms-zombie-1", "body-zombie-1", "head-zombie-1", "legs-zombie-1"};
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private PartRepository partRepository;
	
	public Profile createNewProfile(User user) {
		Profile profile = new Profile();
		profile.setUser(user);
		//TEST
		profile.setParts(new ArrayList<Part>());
		if(user.getUsername().equalsIgnoreCase("root")) {
			profile.setBones(100);
			profile.setRoots(100);
			profile.setFlesh(100);
			profile.setLeaves(100);
			profile.getParts().addAll(partRepository.findAll());
		}else {
			for(String partName : initialParts) {
				Part part = partRepository.getByPrefabName(partName);
				profile.getParts().add(part);
			}
			
		}
		
		
		profile = profileRepository.save(profile);
		return profile;
	}
	
	public Profile getProfileByUserId(Long userId) {
		Optional<Profile> profile = profileRepository.findByUserId(userId);
		if(profile.isPresent()) {
			return profile.get();
		}else {
			throw new IllegalStateException("Profile not found");
		}
	}
	
	
}
