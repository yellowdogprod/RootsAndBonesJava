package com.yellowdogprod.rootsandbones.zone;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.yellowdogprod.rootsandbones.parts.Part;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Zone {

	@Id
	private Long id;
	private String name;
	private Integer flesh;
	private Integer bones;
	private Integer leaves;
	private Integer roots;
	private Integer level;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "zone_part",
           joinColumns = { @JoinColumn(name = "zone_id") },
           inverseJoinColumns = { @JoinColumn(name = "part_id") })
	private List<Part> parts;
	
}
