package com.ros.inventory.model.stock;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ros.inventory.model.purchaseorder.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ClosingStock {
  @EmbeddedId
  private ClosingStockId id;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  @MapsId("productId")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "stock_id", referencedColumnName = "id")
  @MapsId("stockId")
  private Stock stock;

  @NotNull
  private Double pricePerUnit;

  @NotEmpty
  private String unitMeasurement;

  @NotNull
  private Integer quantity;
  
  public String toString() {
	  return "product id: "+id.getProductId()+", stock id: "+id.getStockId();
  }
}
