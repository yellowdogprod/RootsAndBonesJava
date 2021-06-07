package com.yellowdogprod.rootsandbones.zone;

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
import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.creature.CreatureService;
import com.yellowdogprod.rootsandbones.profilezone.ProfileZoneService;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/zones")
@AllArgsConstructor
public class ZoneController {

	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private ProfileZoneService profileZoneService;
	
	
	@GetMapping(path="/fight/{zoneId}")
	public ResponseBean<InvasionCombatBean> getInvasionCombatInstance(HttpSession session, @PathVariable(name="zoneId") Long zoneId){
		Long userId = (Long)session.getAttribute("userId");
		return zoneService.getCreaturesToFight(zoneId);
	}
	
	@PostMapping(path="/fight/{zoneId}")
	public ResponseBean<EndCombatBean> fightEnded(HttpSession session, @PathVariable(name="zoneId") Long zoneId,
			@RequestBody EndCombatBean ecb){
		Long userId = (Long)session.getAttribute("userId");
		return zoneService.fightEnded(userId, zoneId, ecb);
	}
	
	@GetMapping(path="/{zoneId}")
	public ResponseBean<Zone> getZone(HttpSession session, @PathVariable(name="zoneId") Long zoneId){
		Long userId = (Long)session.getAttribute("userId");
		return zoneService.get(zoneId);
	}
	
	@GetMapping(path = "/getStartingZones")
	public ResponseBean<List<Zone>> getStartingZones(HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return zoneService.getStartingZones();
	}
	
}
