package com.ros.inventory.controller.dto;

import java.util.Date;

import com.ros.inventory.model.supplier.SupplierType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderSubmittedDto {

	private long poNumber;
	private Date purchaseOrderDate;
	private String supplierName;
	private SupplierType supplierType;
	private float value;

}
