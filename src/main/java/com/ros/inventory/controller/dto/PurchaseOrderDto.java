package com.ros.inventory.controller.dto;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;
import com.ros.inventory.model.supplier.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDto {

	private UUID poId;

	private long poNumber;

	private Date poDate;

	private String supplierName;

	private double value;

	private PurchaseOrderType status;

	private String invoiceStatus;

	private Supplier supplier;

	private List<PurchasedProduct> products = new ArrayList<PurchasedProduct>();

}
