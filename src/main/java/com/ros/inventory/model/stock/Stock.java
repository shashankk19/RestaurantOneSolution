package com.ros.inventory.model.stock;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.purchaseorder.SiteTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private double openingValue;
	private double closingValue;
	private double netSales;

	@Enumerated(EnumType.STRING)
	private StockType stockType = StockType.OPEN;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="stock_id")
	private List<PurchaseOrder> purchaseOrder;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="stock_id")
	private List<Wastage> wastages;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="stock_id")
	private List<SiteTransfer> siteTransfers;
}
