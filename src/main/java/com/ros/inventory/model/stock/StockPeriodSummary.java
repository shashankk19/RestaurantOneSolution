package com.ros.inventory.model.stock;

import java.util.List;

import com.ros.inventory.model.purchaseorder.SiteTransfer;
import com.ros.inventory.model.purchaseorder.TransferType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StockPeriodSummary extends Stock {
	
		
	private double costOfSales;
	private double netSales;
	private double purchases;
	private double siteTransferInValue;
	private double siteTransferOutValue;
	private double grossProfitValue;
	private double grossProfitPercentage;
	
	
	public void setCostOfSales(double costOfSales) {
		this.costOfSales = costOfSales;
	}
	public void setNetSales(double netSales) {
		this.netSales = netSales;
	}
	public void setPurchases(double purchases) {
		this.purchases = purchases;
	}
	public void setSiteTransferOutValue(double siteTransferOutValue) {
		this.siteTransferOutValue = siteTransferOutValue;
	}
	public void setSiteTransferInValue(double siteTransferInValue) {
		this.siteTransferInValue = siteTransferInValue;
	}
	public void setGrossProfitValue(double grossProfitValue) {
		this.grossProfitValue = grossProfitValue;
	}
	public void setGrossProfitPercentage(double grossProfitPercentage) {
		this.grossProfitPercentage = grossProfitPercentage;
	}

	
	public double getSiteTransferInValue() {
		
		double total=0;
		List<SiteTransfer> siteTransfers = this.getSiteTransfers();
		
		for(SiteTransfer transfer: siteTransfers) {
			
			if(transfer.getTransferType().equals(TransferType.TRANSFERIN)) {
				total += transfer.getTotalValue();
			}
		}
		return total;
	}
	public double getSiteTransferOutValue() {
		
		double total=0;
		List<SiteTransfer> siteTransfers = this.getSiteTransfers();
		
		for(SiteTransfer transfer: siteTransfers) {
			
			if(transfer.getTransferType().equals(TransferType.TRANSFEROUT)) {
				total += transfer.getTotalValue();
			}
		}
		return total;

	}
	public double getCostOfSales() {
		return costOfSales;
	}
	public double getNetSales() {
		return netSales;
	}
	public double getPurchases() {
		return purchases;
	}
	public double getGrossProfitValue() {
		return grossProfitValue;
	}
	
	public double getGrossProfitPercentage() {
		return grossProfitPercentage;
	}

}
