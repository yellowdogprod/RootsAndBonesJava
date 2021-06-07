package com.yellowdogprod.rootsandbones.creaturerecipe;

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
public class CreatureRecipePartId implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "creature_recipe_id")
    private Long creatureRecipeId;
 
    @Column(name = "part_id")
    private Long partId;    
    
    @Column(name = "part_slot_index")
	private Integer partSlotIndex;	

    public CreatureRecipePartId(Long cid, Long pid, Integer psi) {
    	creatureRecipeId = cid;
    	partId = pid;
    	partSlotIndex = psi;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CreatureRecipePartId that = (CreatureRecipePartId) o;
        return Objects.equals(creatureRecipeId, that.creatureRecipeId) &&
               Objects.equals(partId, that.partId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(creatureRecipeId, partId);
    }
    
	
}
