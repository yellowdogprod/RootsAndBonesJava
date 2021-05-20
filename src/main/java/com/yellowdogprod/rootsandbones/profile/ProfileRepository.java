package com.yellowdogprod.rootsandbones.profile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

	Optional<Profile> findByUserId(Long userId);
	
	
	
}
