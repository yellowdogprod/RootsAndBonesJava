package com.yellowdogprod.rootsandbones.creature;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class CreaturePartId implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "creature_id")
    private Long creatureId;
 
    @Column(name = "part_id")
    private Long partId;    
    
    @Column(name = "part_slot_index")
	private Integer partSlotIndex;	

    public CreaturePartId(Long cid, Long pid, Integer psi) {
    	creatureId = cid;
    	partId = pid;
    	partSlotIndex = psi;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CreaturePartId that = (CreaturePartId) o;
        return Objects.equals(creatureId, that.creatureId) &&
               Objects.equals(partId, that.partId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(creatureId, partId);
    }
    
	
}
