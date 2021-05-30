package com.yellowdogprod.rootsandbones.parts;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yellowdogprod.rootsandbones.creature.Creature;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.zone.Zone;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Part {

	@Id @GeneratedValue
	private Long id;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "parts", fetch = FetchType.LAZY)
	private List<Creature> creatures;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "parts", fetch = FetchType.LAZY)
	private List<Zone> zones;
	
	private String name;
	private String type;
	private String prefabName;
	private Integer level;
	private Integer flesh;
	private Integer bones;
	private Integer roots;
	private Integer leaves;
	
	
}

