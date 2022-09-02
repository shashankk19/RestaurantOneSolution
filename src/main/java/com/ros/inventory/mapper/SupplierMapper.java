package com.ros.inventory.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.controller.dto.supplier.ExternalSupplierDto;
import com.ros.inventory.controller.dto.supplier.InternalSupplierDto;
import com.ros.inventory.controller.dto.supplier.ProductMasterDto;
import com.ros.inventory.model.supplier.ProductMaster;
import com.ros.inventory.model.supplier.Supplier;

import org.mapstruct.Mapping;

@Mapper
public interface SupplierMapper {

	Supplier updateSupplierFromDto(ExternalSupplierDto externalSupplierDto, @MappingTarget Supplier supplier);

	Supplier updateSupplierFromDto(InternalSupplierDto internalSupplierDto, @MappingTarget Supplier supplier);

	ExternalSupplierDto convertToExternalSupplierDto(SupplierDto supplierDto);

	InternalSupplierDto convertToInternalSupplierDto(SupplierDto supplierDto);

	ProductMaster convertToProductMaster(ProductMasterDto productMasterDto);
	
	@Mapping(source = "supplier.supplierName",target = "supplierName")
	@Mapping(source = "supplier.type",target = "type")
	@Mapping(source = "supplier.email",target = "email")
	@Mapping(source = "supplier.phoneNumber",target = "phoneNumber")
	public SupplierDto convertToSupplierDto(Supplier supplier);

}
