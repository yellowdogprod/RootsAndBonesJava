package com.yellowdogprod.rootsandbones.profilezone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileZoneRepository extends JpaRepository<ProfileZone, Long> {

	@Query("SELECT  pz " +
            "FROM ProfileZone pz WHERE pz.profile.id = ?1")
	public List<ProfileZone> getProfileZonesForProfile(Long profileId);
	
}
