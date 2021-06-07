package com.yellowdogprod.rootsandbones.profilezone;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/profilezone")
@AllArgsConstructor
public class ProfileZoneController {
	
	@Autowired
	private ProfileZoneService profileZoneService;
	
	@GetMapping(path = "/checkStartingZone")
	public ResponseBean<Boolean> checkStartingZone(HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return profileZoneService.checkStartingZone(userId);
	}
	
	@GetMapping(path = "/selectStartingZone/{zoneId}")
	public ResponseBean<Boolean> selectStartingZone(HttpSession session, @PathVariable("zoneId") Long zoneId){
		Long userId = (Long)session.getAttribute("userId");
		return profileZoneService.selectStartingZone(userId, zoneId);
	}
	
	@GetMapping
	public ResponseBean<List<ProfileZoneBean>> getInfestedZones(HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return profileZoneService.getInfestedZones(userId);
	}
	
}

