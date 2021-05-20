package com.yellowdogprod.rootsandbones.profile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yellowdogprod.rootsandbones.parts.Part;
import com.yellowdogprod.rootsandbones.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Profile {

	@Id @GeneratedValue
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_part",
           joinColumns = { @JoinColumn(name = "profile_id") },
           inverseJoinColumns = { @JoinColumn(name = "part_id") })
	private List<Part> parts;
	
	private Integer flesh = 10;
	private Integer bones = 10;
	private Integer leaves = 10;
	private Integer roots = 10;
	
}
