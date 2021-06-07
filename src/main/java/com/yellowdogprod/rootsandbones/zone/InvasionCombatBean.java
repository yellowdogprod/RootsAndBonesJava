package com.yellowdogprod.rootsandbones.zone;

import java.util.List;

import com.yellowdogprod.rootsandbones.parts.Part;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InvasionCombatBean {

	private float fleshRatio;
	private float bonesRatio;
	private float leavesRatio;
	private float rootsRatio;
	
	private int numberOfCreatures;
	
	private List<Part> parts;

}
