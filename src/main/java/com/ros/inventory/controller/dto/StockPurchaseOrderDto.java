package com.ros.inventory.controller.dto;

import java.util.Date;

import com.ros.inventory.model.purchaseorder.PurchaseOrderType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockPurchaseOrderDto {

	private long poNumber;
	private Date purchaseOrderDate;
	private String supplierName;
	private PurchaseOrderType purchaseOrderType;
	private float value;
	private String invoiceStatus;

}
