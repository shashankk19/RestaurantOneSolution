package com.ros.inventory.controller.dto;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.controller.dto.supplier.AddressDto;
import com.ros.inventory.controller.dto.supplier.BankDetailsDto;
import com.ros.inventory.controller.dto.supplier.BasicInformationDto;
import com.ros.inventory.controller.dto.supplier.ProductMasterDto;
import com.ros.inventory.model.supplier.PrimaryContact;
import com.ros.inventory.model.supplier.SupplierType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {

	private UUID id;
	
	private SupplierType type;
	
	private String supplierName;
	
	private String profilePic;

	private String email;
	
	private long phoneNumber;
	
	private String restaurantName;

	private AddressDto generalAddress;

	private BankDetailsDto bankDetails;
	
	private BasicInformationDto basicInformation;	
	
	private PrimaryContact primaryContact;
	
	private List<ProductMasterDto> products;
	
}
