package com.yellowdogprod.rootsandbones.profile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.user.User;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	public Profile createNewProfile(User user) {
		Profile profile = new Profile();
		profile.setUser(user);
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
