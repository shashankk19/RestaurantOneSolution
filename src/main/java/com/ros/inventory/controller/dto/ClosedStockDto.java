package com.ros.inventory.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClosedStockDto {

	private Date startDate;
	private Date endDate;
	private double openingValue;
	private double closingValue;
	private double netSales;

}
