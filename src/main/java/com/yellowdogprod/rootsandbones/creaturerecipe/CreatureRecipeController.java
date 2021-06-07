package com.yellowdogprod.rootsandbones.creaturerecipe;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;
import com.yellowdogprod.rootsandbones.creature.CreatureBean;
import com.yellowdogprod.rootsandbones.creature.CreatureRequest;
import com.yellowdogprod.rootsandbones.creature.CreatureService;
import com.yellowdogprod.rootsandbones.user.User;
import com.yellowdogprod.rootsandbones.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/recipes")
@AllArgsConstructor
public class CreatureRecipeController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CreatureRecipeService crs;
	
	@PostMapping
	public ResponseBean<CreatureRecipe> create(@RequestBody CreatureRecipeRequest req, HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return crs.createRecipe(userId, req);
	}
	
	@GetMapping
	public ResponseBean<List<CreatureRecipeBean>> getRecipes(HttpSession session){
		Long userId = (Long)session.getAttribute("userId");
		return crs.getRecipes(userId);
	}
	
	
}
