package com.ros.inventory.model.stock;

import javax.persistence.Entity;

import com.ros.inventory.model.purchaseorder.PurchaseOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StockPurchaseOrder extends PurchaseOrder{

    private String invoiceStatus;
}
