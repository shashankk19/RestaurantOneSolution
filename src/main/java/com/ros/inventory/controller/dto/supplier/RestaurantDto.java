package com.ros.inventory.controller.dto.supplier;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.model.invoice.Invoice;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.supplier.ResturantAddress;
import com.ros.inventory.model.supplier.Supplier;

import lombok.Data;

@Data
public class RestaurantDto {

	private UUID restaurantId;

	private String restaurantName;

	private List<Supplier> supplier;

	private List<PurchaseOrder> purchase;

	private List<Invoice> invoice;

	private ResturantAddress resturantAddress;

}
