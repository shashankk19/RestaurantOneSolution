package com.ros.inventory.model.purchaseorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.inventory.model.invoice.Invoice;
import com.ros.inventory.model.supplier.Restaurant;
import com.ros.inventory.model.stock.Stock;
import com.ros.inventory.model.supplier.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PurchaseOrder {
    
	public PurchaseOrder(Supplier supplier, List<PurchasedProduct> products, PurchaseOrderType type) {
		this.supplier = supplier;
		this.products = products;
		this.purchaseOrderType = type;
	}
	
	public PurchaseOrder(Supplier supplier, List<PurchasedProduct> products, PurchaseOrderType type, Stock stock) {
		this.supplier = supplier;
		this.products = products;
		this.purchaseOrderType = type;
		this.stock = stock;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID poId;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long poNumber;
    
    @OneToOne
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;
    
    @Enumerated(EnumType.STRING)
    private PurchaseOrderType purchaseOrderType;

    private Date purchaseOrderDate;
    
    private Date rejectionDate;
    
    private Date approvedDate;
    
    private String reason;
    
    private String comment;
    
    @OneToMany
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "poId")
    private List<PurchasedProduct> products = new ArrayList<PurchasedProduct>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="invoice_id")
    private Invoice invoice;
    
    @OneToMany(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
	@JoinColumn(name ="purchase_order_id")
	private List<Attachments>  attachements = new ArrayList<Attachments>();
    
 
    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    private String createdBy;
    
    private String approvedBy;
    
    private String modifiedBy;

    private float value;
   
}
