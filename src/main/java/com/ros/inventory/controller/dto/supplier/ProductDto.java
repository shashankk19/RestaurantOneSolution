package com.ros.inventory.controller.dto.supplier;

import com.ros.inventory.model.purchaseorder.ProductType;

import lombok.Data;

@Data
public class ProductDto {

	private String name;

	private long productCode;

	private ProductType type;

}
