package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ros.inventory.controller.dto.SiteTransferDto;
import com.ros.inventory.model.purchaseorder.SiteTransfer;

@Mapper
public interface SiteTransferMapper {
	
	@Mapping(source="siteTransfer.date",target="date")
	@Mapping(source="siteTransfer.supplierName",target="supplierName")
	@Mapping(source="siteTransfer.transferType",target="transferType")
	@Mapping(source="siteTransfer.noOfProducts",target="noOfProducts")
	@Mapping(source="siteTransfer.totalValue",target="totalValue")
	public SiteTransferDto convertToSiteTransferDto(SiteTransfer siteTransfer);
	

}
