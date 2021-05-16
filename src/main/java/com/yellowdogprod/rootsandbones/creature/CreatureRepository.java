package com.yellowdogprod.rootsandbones.creature;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long>{

	@Query("SELECT c FROM Creature c WHERE c.profile.id = ?1")
    Optional<List<Creature>> getProfileCreatures(Long profileId);
	
}
