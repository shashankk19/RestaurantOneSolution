package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ros.inventory.controller.dto.PurchaseOrderDto;
import com.ros.inventory.model.stock.StockPurchaseOrder;
import com.ros.inventory.controller.dto.PurchaseOrderApprovedDto;
import com.ros.inventory.controller.dto.PurchaseOrderDraftDto;
import com.ros.inventory.controller.dto.PurchaseOrderRejectedDto;
import com.ros.inventory.controller.dto.PurchaseOrderSubmittedDto;
import com.ros.inventory.controller.dto.StockPurchaseOrderDto;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

import org.mapstruct.MappingTarget;

@Mapper
public interface PurchaseOrderMapper {

	
	@Mapping(source = "stockPurchaseOrder.poNumber", target = "poNumber")
	@Mapping(source = "stockPurchaseOrder.purchaseOrderDate", target = "poDate")
	@Mapping(source = "stockPurchaseOrder.supplier.supplierName", target = "supplierName")
	@Mapping(source = "stockPurchaseOrder.value", target = "value")
	@Mapping(source = "stockPurchaseOrder.purchaseOrderType", target = "status")
	@Mapping(source = "stockPurchaseOrder.invoiceStatus", target = "invoiceStatus")
	public PurchaseOrderDto convertToPurchaseOrderDto(StockPurchaseOrder stockPurchaseOrder);

	@Mapping(source = "purchaseOrder.poNumber", target = "poNumber")
	@Mapping(source = "purchaseOrder.purchaseOrderDate", target = "purchaseOrderDate")
	@Mapping(source = "purchaseOrder.supplier.supplierName", target = "supplierName")
	@Mapping(source = "purchaseOrder.supplier.type", target = "supplierType")
	@Mapping(source = "purchaseOrder.value", target = "value")
	public PurchaseOrderDraftDto convertToDraftDto(PurchaseOrder purchaseOrder);

	public PurchaseOrderSubmittedDto convertToSubmittedDto(PurchaseOrder purchaseOrder);

	@Mapping(source = "purchaseOrder.purchaseOrderType", target = "status")
	public PurchaseOrderApprovedDto convertToApprovedDto(PurchaseOrder purchaseOrder);

	@Mapping(source = "purchaseOrder.rejectionDate", target = "rejectionDate")
	public PurchaseOrderRejectedDto convertToRejectedDto(PurchaseOrder purchaseOrder);

	PurchaseOrder convertToPurchaseOrderEntity (PurchaseOrderDto purchaseorderdto, @MappingTarget PurchaseOrder purchaseorder);
	
	@Mapping(source="purchaseOrder.poNumber", target="poNumber")
	@Mapping(source="purchaseOrder.purchaseOrderDate",target="purchaseOrderDate")
	@Mapping(source="purchaseOrder.supplier.supplierName",target="supplierName")
	@Mapping(source="purchaseOrder.purchaseOrderType",target="purchaseOrderType")
	@Mapping(source="purchaseOrder.value", target="value")
	//@Mapping(source="purchaseOrder.invoiceStatus", target="invoiceStatus")
	public StockPurchaseOrderDto convertToStockPurchaseOrderDto(PurchaseOrder purchaseOrder);
	
	@Mapping(source="purchaseOrder.poId", target="poId")
	@Mapping(source = "purchaseOrder.purchaseOrderType", target = "status")
	public PurchaseOrderDto convertToPurchaseOrderDto(PurchaseOrder purchaseOrder);


}
