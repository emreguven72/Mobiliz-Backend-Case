package com.emregvn.mobilizbackendcase.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groupId")
	private int groupId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne()
	@JoinColumn(name = "companyId")
	private Company company;
	
	@ManyToOne()
	@JoinColumn(name = "fleetId")
	private Fleet fleet;
	
	@OneToMany(mappedBy = "group")
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "parentGroup")
	private List<Group> subGroups;
	
	@ManyToOne()
	private Group parentGroup;

}
