package com.yellowdogprod.rootsandbones.zone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long>{

	@Query("SELECT z FROM Zone z WHERE z.level = 1")
	public List<Zone> getStartingZones();
	
}
