package com.ros.inventory.model.supplier;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ros.inventory.model.invoice.Invoice;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Restaurant implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurant_id")
	private UUID restaurantId;
	
	@Column(name ="restaurant_name" )
	private String restaurantName;
	
	 @OneToMany(targetEntity =Supplier.class , cascade=CascadeType.ALL )
     @JoinColumn(name= "restaurant_fk" ,referencedColumnName = "restaurant_id")
	 private List<Supplier> supplier;

	 @OneToMany(targetEntity =PurchaseOrder.class , cascade=CascadeType.ALL )
     @JoinColumn(name= "restaurant_fk" ,referencedColumnName = "restaurant_id")
	 private List<PurchaseOrder>  purchase;
	 
	 @OneToMany(mappedBy = "restaurant" , cascade=CascadeType.ALL )
	 private List<Invoice>  invoice;
	 
	 @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
	 @JoinColumn(name ="restaurantaddress_id")
	 private ResturantAddress resturantAddress;

	public UUID getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(UUID restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public List<Supplier> getSupplier() {
		return supplier;
	}

	public void setSupplier(List<Supplier> supplier) {
		this.supplier = supplier;
	}

	public List<PurchaseOrder> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<PurchaseOrder> purchase) {
		this.purchase = purchase;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}

	public ResturantAddress getResturantAddress() {
		return resturantAddress;
	}

	public void setResturantAddress(ResturantAddress resturantAddress) {
		this.resturantAddress = resturantAddress;
	}
}

