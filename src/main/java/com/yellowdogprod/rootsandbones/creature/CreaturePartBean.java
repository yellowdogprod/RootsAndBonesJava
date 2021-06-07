package com.yellowdogprod.rootsandbones.creature;

import java.util.List;

import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.profile.Profile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CreaturePartBean {

	private Part part;
	private Integer partSlotIndex;
	
}
