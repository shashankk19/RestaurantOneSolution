package com.ros.inventory.controller.dto;

import java.util.UUID;

import com.ros.inventory.model.stock.StockType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {

	private UUID stockId;
	private StockType stockType;

}
