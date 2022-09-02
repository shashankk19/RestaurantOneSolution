package com.ros.inventory.controller.dto;

import java.util.Date;

import com.ros.inventory.model.purchaseorder.TransferType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteTransferDto {

	private Date date;
	private String supplierName;
	private TransferType transferType;
	private int noOfProducts;
	private float totalValue;

}
