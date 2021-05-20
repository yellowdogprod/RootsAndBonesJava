package com.yellowdogprod.rootsandbones.parts;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.profile.ProfileService;
import com.yellowdogprod.rootsandbones.user.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/parts")
@AllArgsConstructor
public class PartController {

	@Autowired
	private PartService partService;
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping
	public ResponseBean<List<Part>> getAllParts(){
		return partService.getAllParts();
	}
	
	@GetMapping(path = "/profile")
	public ResponseBean<List<Part>> getPartsByType(HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return partService.getProfileParts(userId);
	}
	
	@PutMapping
	public ResponseBean<Part> discoverPart(HttpSession session, @RequestParam(name="partId") Long partId){
		Long userId = (Long)session.getAttribute("userId");
		return partService.discoverPart(userId, partId);
	}
	
}
