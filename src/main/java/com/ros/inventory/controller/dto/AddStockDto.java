package com.ros.inventory.controller.dto;

import java.util.Date;
import java.util.UUID;

public class AddStockDto {

	private UUID stockId;
	private Date startDate;
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public UUID getStockId() {
		return stockId;
	}

	public void setStockId(UUID stockId) {
		this.stockId = stockId;
	}

}
