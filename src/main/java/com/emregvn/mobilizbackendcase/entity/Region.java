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
@Table(name = "regions")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "regionId")
	private int regionId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne()
	@JoinColumn(name = "companyId")
	private Company company;
	
	@OneToMany(mappedBy = "region")
	private List<Fleet> fleets;
	
	@OneToMany(mappedBy = "region")
	private List<Vehicle> vehicles;
	
}
