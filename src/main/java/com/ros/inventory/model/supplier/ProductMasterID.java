package com.ros.inventory.model.supplier;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProductMasterID implements Serializable {

	private UUID productId;
	private UUID supplierId;
	public UUID getProductId() {
		return productId;
	}
	public void setProductId(UUID productId) {
		this.productId = productId;
	}
	public UUID getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(UUID supplierId) {
		this.supplierId = supplierId;
	}
	
	
}
