package com.ros.inventory.controller.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

	private String supplierName;
	private LocalDate invoiceDate;
	private long poNumber;
	private double total;

}
