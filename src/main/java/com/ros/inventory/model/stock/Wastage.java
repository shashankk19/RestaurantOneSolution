package com.ros.inventory.model.stock;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.ros.inventory.model.purchaseorder.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wastage {

//	@EmbeddedId
//	private ClosingStockId id;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID wastageId;
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	//@MapsId("productId")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "stock_id", referencedColumnName = "id")
	//@MapsId("stockId")
	private Stock stock;

	private int quantity;
	private Double pricePerUnit;
}
