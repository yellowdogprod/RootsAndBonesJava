package com.yellowdogprod.rootsandbones.creature;

import com.yellowdogprod.rootsandbones.beans.ResourcesBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreatureRequest {

	private Creature creature;
	private ResourcesBean cost;
	private VectorBean position;
	private Boolean deploy;
	
}

