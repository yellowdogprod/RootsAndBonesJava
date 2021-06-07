package com.yellowdogprod.rootsandbones.creaturerecipe;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yellowdogprod.rootsandbones.creature.Creature;

@Repository
public interface CreatureRecipeRepository extends JpaRepository<CreatureRecipe, Long>{

	@Query("SELECT cr FROM CreatureRecipe cr WHERE cr.profile.id = ?1")
    Optional<List<CreatureRecipe>> getProfileRecipes(Long profileId);
	
}
