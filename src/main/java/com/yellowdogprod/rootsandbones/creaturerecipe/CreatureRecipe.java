package com.yellowdogprod.rootsandbones.creaturerecipe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
public class CreatureRecipe {

	@Id @GeneratedValue
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
    private Profile profile;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "creatureRecipe", cascade = CascadeType.ALL)
	private List<CreatureRecipePart> parts = new ArrayList<CreatureRecipePart>();
	
	public void addPart(Part p, Integer psi) {
		CreatureRecipePart cp = new CreatureRecipePart(this, p, psi);
		parts.add(cp);
		p.getCreatureRecipeParts().add(cp);
	}
	
}
