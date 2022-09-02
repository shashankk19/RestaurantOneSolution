package com.ros.inventory.controller.dto.supplier;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.ros.inventory.model.supplier.SupplierType;

import lombok.Data;

@Data
public class ExternalSupplierDto {

	private UUID id;

	private SupplierType type;

	private String profilePic;

	@NotNull
	private BasicInformationDto basicInformation;

	@NotNull
	private AddressDto generalAddress;

	@NotNull
	private BankDetailsDto bankDetails;

	private List<ProductMasterDto> products;

}
