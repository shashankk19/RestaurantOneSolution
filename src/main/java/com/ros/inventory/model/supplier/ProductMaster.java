package com.ros.inventory.model.supplier;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ros.inventory.model.purchaseorder.Product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductMaster {

	@EmbeddedId
	private ProductMasterID id;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@MapsId("productId")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	@MapsId("supplierId")
	@JsonManagedReference
	private Supplier supplier;
	
	private String unitMeasurement;

	@NotNull
	private Double pricePerUnit;

	@NotNull
	private Double vat;

	// @Formula("price_per_unit * (100 + vat) / 100")
	private double actualPrice;

	@NotNull(message = "Effective Date is compulsory to be enetered for a new entry")
	private Date effectiveDate;

	@PrePersist
	public void calculateActualPrice() {
		this.actualPrice = pricePerUnit * (100 + vat) / 100;
	}

	public ProductMasterID getId() {
		return id;
	}

	public void setId(ProductMasterID id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getUnitMeasurement() {
		return unitMeasurement;
	}

	public void setUnitMeasurement(String unitMeasurement) {
		this.unitMeasurement = unitMeasurement;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Double getVat() {
		return vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}
