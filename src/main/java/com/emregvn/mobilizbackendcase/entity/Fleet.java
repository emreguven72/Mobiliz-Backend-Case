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
@Table(name = "fleets")
public class Fleet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fleetId")
	private int fleetId;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne()
	@JoinColumn(name = "companyId")
	private Company company;
	
	@ManyToOne()
	@JoinColumn(name = "regionId")
	private Region region;
	
	@OneToMany(mappedBy = "fleet")
	private List<Group> groups;
	
	@OneToMany(mappedBy = "fleet")
	private List<Vehicle> vehicles;
	
}
