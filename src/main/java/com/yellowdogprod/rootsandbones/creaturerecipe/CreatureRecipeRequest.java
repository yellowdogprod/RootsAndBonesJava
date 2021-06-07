package com.yellowdogprod.rootsandbones.creaturerecipe;

import java.util.ArrayList;
import java.util.List;

import com.yellowdogprod.rootsandbones.beans.ResourcesBean;
import com.yellowdogprod.rootsandbones.beans.VectorBean;
import com.yellowdogprod.rootsandbones.creature.CreaturePartBean;
import com.yellowdogprod.rootsandbones.parts.Part;

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
public class CreatureRecipeRequest {


	private List<CreaturePartBean> parts = new ArrayList<CreaturePartBean>();
	
	
}

