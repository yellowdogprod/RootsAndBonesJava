package com.yellowdogprod.rootsandbones.creature;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@SequenceGenerator(name="creature_sequence",
	sequenceName = "creature_sequence", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creature_sequence")
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
    private Profile profile;
	
	private String parts;
	private Float x;
	private Float y;
	private Boolean onField = false;
	
	
}
