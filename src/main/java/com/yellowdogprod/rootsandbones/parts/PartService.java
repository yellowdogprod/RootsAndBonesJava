package com.yellowdogprod.rootsandbones.parts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.profile.ProfileRepository;
import com.yellowdogprod.rootsandbones.user.UserService;

@Service
public class PartService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PartRepository partRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public void save(Part p) {
		partRepository.save(p);
	}
	
	public ResponseBean<List<Part>> getAllParts(){
		List<Part> parts = partRepository.findAll();
		return new ResponseBean<List<Part>>(parts);
	}
	
	public Part getPartById(Long id) {
		Optional<Part> part = partRepository.findById(id);
		if(part.isPresent()) return part.get();
		return null;
	}
	
	public ResponseBean<Part> discoverPart(Long userId, Long partId) {
		Optional<Profile> profile = profileRepository.findByUserId(userId);
		if(profile.isPresent()) {
			Part part = getPartById(partId);
			if(part == null) {
				throw new IllegalStateException("Part not found");
			}else {
				profile.get().getParts().add(part);
				profileRepository.save(profile.get());
				return new ResponseBean<Part>(part);
			}
		}else {
			throw new IllegalStateException("Profile not found");
		}
	}
	
	public ResponseBean<List<Part>> getProfileParts(Long userId){
		Optional<Profile> profile = profileRepository.findByUserId(userId);
		if(profile.isPresent()) {
			return new ResponseBean<List<Part>>(profile.get().getParts());
		}else {
			throw new IllegalStateException("Profile not found");
		}
	}
	
}
