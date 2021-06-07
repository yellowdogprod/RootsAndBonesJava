package com.yellowdogprod.rootsandbones.creature;

import java.util.List;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Entity
public class CreaturePart {
	
	@EmbeddedId
	private CreaturePartId id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("creatureId")
	private Creature creature;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("partId")
	private Part part;
	
    public CreaturePart(Creature c, Part p, Integer psi) {
        this.creature = c;
        this.part = p;
        this.id = new CreaturePartId(creature.getId(), part.getId(), psi);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        CreaturePart that = (CreaturePart) o;
        return Objects.equals(creature, that.creature) &&
               Objects.equals(part, that.part);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(creature, part);
    }
	
}
