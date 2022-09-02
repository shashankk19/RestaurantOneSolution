package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ros.inventory.controller.dto.PurchasedProductDto;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;

@Mapper
public interface PurchasedProductMapper {

	@Mapping(source="purchasedProduct.id", target="productId")
	@Mapping(source="purchasedProduct.quantity", target="quantity")
	public PurchasedProductDto convertToPurchasedProductDto(PurchasedProduct purchasedProduct);
}
