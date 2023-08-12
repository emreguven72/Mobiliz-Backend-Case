package com.emregvn.mobilizbackendcase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "vehicleId")
	private int vehicleId;
	
	//plaka
	@Column(name = "plateNumber")
	private String plateNumber;
	
	//Şase numarası
	@Column(name = "chassisNumber")
	private String chassisNumber;
	
	//etiket
	@Column(name = "label")
	private String label;
	
	//marka
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "modelYear")
	private int modelYear;
	
	@ManyToOne()
	@JoinColumn(name = "companyId")
	private Company company;
	
	@ManyToOne()
	@JoinColumn(name = "groupId")
	private Group group;
	
}
