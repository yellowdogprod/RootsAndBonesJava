package com.yellowdogprod.rootsandbones.beans;

import com.yellowdogprod.rootsandbones.parts.Part;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ResourcesBean {

	public int flesh;
	public int bones;
	public int leaves;
	public int roots;
	
	public ResourcesBean() {
		flesh = 0;
		bones = 0;
		leaves = 0;
		roots = 0;
	}
	
	public void addCost(Part p) {
		flesh += p.getFlesh();
		bones += p.getBones();
		leaves += p.getLeaves();
		roots += p.getRoots();
	}
	
}
