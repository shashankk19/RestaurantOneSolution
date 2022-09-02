package com.ros.inventory.model.purchaseorder;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SiteTransfer {
	
	@Id
	private UUID Id;
	private Date date;
	private String supplierName;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="purchase_type")
	private TransferType transferType;
	private int noOfProducts;
	private float totalValue;
	private UUID purchaseOrderId;	
		
}
