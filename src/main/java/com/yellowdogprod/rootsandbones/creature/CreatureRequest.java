package com.yellowdogprod.rootsandbones.creature;

import com.yellowdogprod.rootsandbones.beans.ResourcesBean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreatureRequest {

	private Creature creature;
	private ResourcesBean cost;
	
}
