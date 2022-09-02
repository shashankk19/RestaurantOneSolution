package com.ros.inventory.controller.dto;

import java.util.UUID;

import com.ros.inventory.model.purchaseorder.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WastageDto {

	private UUID wastageId;
	private Product	product;
	private double pricePerUnit;
	private int quantity;

}
