package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

//Mappe

@Mapper
public interface InvoiceMapper {
	
	@Mapping(source = "purchaseOrder.supplier.supplierName",target="supplierName")
	@Mapping(source = "purchaseOrder.invoice.invoiceDate",target="invoiceDate")
	@Mapping(source = "purchaseOrder.poNumber",target="poNumber")
	@Mapping(source = "purchaseOrder.value",target="total")
	public InvoiceDto  convertToInvoiceDto(PurchaseOrder purchaseOrder);
}
