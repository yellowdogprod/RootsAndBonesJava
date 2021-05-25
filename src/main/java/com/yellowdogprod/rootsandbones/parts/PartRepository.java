package com.yellowdogprod.rootsandbones.parts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>{

    @Query("SELECT p FROM Part p" +
            " WHERE p.prefabName = ?1")
	Part getByPrefabName(String prefabName);	
    
    
}
