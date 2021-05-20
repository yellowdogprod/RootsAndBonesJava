package com.yellowdogprod.rootsandbones.creature;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "creature_part",
           joinColumns = { @JoinColumn(name = "creature_id") },
           inverseJoinColumns = { @JoinColumn(name = "part_id") })
	private List<Part> parts;
	private Float x;
	private Float y;
	private Boolean onField = false;
	
	
}
