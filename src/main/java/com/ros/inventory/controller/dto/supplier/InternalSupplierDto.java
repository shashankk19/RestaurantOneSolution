package com.ros.inventory.controller.dto.supplier;

import java.util.UUID;

import com.ros.inventory.model.supplier.Address;
import com.ros.inventory.model.supplier.PrimaryContact;
import com.ros.inventory.model.supplier.SupplierType;

import lombok.Data;

@Data
public class InternalSupplierDto {

	private UUID id;

	private SupplierType type;

	private String restaurantName;

	private BasicInformationDto basicInformation;

	private Address generalAddress;

	private PrimaryContact primaryContact;

//	private List<ProductMasterDto> products;
}
