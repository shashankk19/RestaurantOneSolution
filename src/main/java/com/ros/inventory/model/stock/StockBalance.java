package com.ros.inventory.model.stock;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StockBalance {
	private UUID productId;
	private String productName;
	private int openingQty;
	private int orderedQty;
	private int wastedQty;
	private int totalQty;

	// method to calculate total:
	public int getTotal() {
		return openingQty + orderedQty - wastedQty;
	}
}
