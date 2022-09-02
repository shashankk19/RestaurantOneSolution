package com.ros.inventory.model.purchaseorder;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
//	@NotBlank(message = "name cannot be blank")
	private String name;
	
	private long productCode;

//	@NotNull
	@Enumerated(EnumType.STRING)
	private ProductType type;
	
	private String unitMeasurement;


	private Double pricePerUnit;
	
	
}
