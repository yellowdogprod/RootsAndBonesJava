package com.yellowdogprod.rootsandbones.zone;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.creature.CreatureService;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/zones")
@AllArgsConstructor
public class ZoneController {

	@Autowired
	private ZoneService zoneService;
	
	@GetMapping(path="/fight/{zoneId}")
	public ResponseBean<List<Creature>> getProfileCreatures(HttpSession session, @PathVariable(name="zoneId") Long zoneId){
		Long userId = (Long)session.getAttribute("userId");
		return zoneService.getCreaturesToFight(zoneId);
	}
	
	@GetMapping(path="/{zoneId}")
	public ResponseBean<Zone> getZone(HttpSession session, @PathVariable(name="zoneId") Long zoneId){
		Long userId = (Long)session.getAttribute("userId");
		return zoneService.get(zoneId);
	}
	
}
