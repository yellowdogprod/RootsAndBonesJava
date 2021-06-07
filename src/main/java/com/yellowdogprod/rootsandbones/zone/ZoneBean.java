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
public class ZoneBean {

	private Long id;
	private String name;
	private Integer flesh;
	private Integer bones;
	private Integer leaves;
	private Integer roots;
	private Integer level;
	
}
