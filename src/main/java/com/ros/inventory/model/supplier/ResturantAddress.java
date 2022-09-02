package com.ros.inventory.model.supplier;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ResturantAddress implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Address_id")
	private UUID RAddressId;
	
	@Column(name ="Street" )
	private String restaurantStreet; 
	
	@Column(name ="City" )
	private String restaurantCity;
	
	@Column(name ="State" )
	private String restaurantState;
	
	@Column(name ="Country" )
	private String restaurantCountry;
	
	@Column(name ="Mobile_no" )
	private long restaurantMob;
	
	@Column(name ="Teliphone_no" )
	private long restaurantTel;
	
	@Column(name ="Email" )
	private String restaurantEmail;
	
	@Column(name ="PinCode" )
	private long restaurantPin;
}