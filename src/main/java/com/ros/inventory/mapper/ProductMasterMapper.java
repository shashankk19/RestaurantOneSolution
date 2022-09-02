package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ros.inventory.controller.dto.supplier.ProductMasterDto;
import com.ros.inventory.model.supplier.ProductMaster;

@Mapper
public interface ProductMasterMapper {
	
	@Mapping(source = "productMaster.id", target = "id")
	@Mapping(source = "productMaster.unitMeasurement",target = "unitMeasurement")
	@Mapping(source = "productMaster.pricePerUnit",target = "pricePerUnit")
	@Mapping(source = "productMaster.vat",target = "vat")
	@Mapping(source = "productMaster.effectiveDate",target = "effectiveDate")
	@Mapping(source = "productMaster.product",target = "product")
//	@Mapping(source = "productMaster.product.productCode",target = "productCode")
//	@Mapping(source = "productMaster.product.name",target = " name")
//	@Mapping(source = "productMaster.product.type",target = "type")
	public ProductMasterDto convertToProductMasterDto(ProductMaster productMaster);
	
	
	public ProductMaster updateProductMaster(ProductMasterDto productMasterDto, @MappingTarget ProductMaster productMaster);

}
