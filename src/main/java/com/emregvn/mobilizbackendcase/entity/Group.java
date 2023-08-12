package com.emregvn.mobilizbackendcase.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Vehicle> vehicles;
	
	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authorization> authorizations;
	
	@OneToMany(mappedBy = "parentGroup", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Group> subGroups;
	
	@ManyToOne()
	private Group parentGroup;

}
