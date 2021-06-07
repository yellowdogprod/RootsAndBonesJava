package com.yellowdogprod.rootsandbones.zone;

import java.util.List;

import com.yellowdogprod.rootsandbones.beans.ResourcesBean;
import com.yellowdogprod.rootsandbones.parts.Part;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EndCombatBean {

	private ResourcesBean droppedResources;
	private boolean playerWon;
	private List<Long> playerDeadCreatures;
	private List<Part> parts; //nuove parti droppate dal player

}
