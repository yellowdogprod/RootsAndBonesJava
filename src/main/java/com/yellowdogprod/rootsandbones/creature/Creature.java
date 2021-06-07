package com.yellowdogprod.rootsandbones.creature;

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
public class Creature {

	@Id @GeneratedValue
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
    private Profile profile;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "creature", cascade = CascadeType.ALL)
	private List<CreaturePart> parts = new ArrayList<CreaturePart>();
	
	private Float x;
	private Float y;
	private Boolean onField = false;
	
	public void addPart(Part p, Integer psi) {
		CreaturePart cp = new CreaturePart(this, p, psi);
		parts.add(cp);
		p.getCreatureParts().add(cp);
	}
	
}
