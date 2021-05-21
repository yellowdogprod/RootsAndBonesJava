package com.yellowdogprod.rootsandbones.zone;

import javax.persistence.Entity;
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

	private Long id;
	private Integer flesh;
	private Integer bones;
	private Integer leaves;
	private Integer roots;
	private Integer level;
	
}
