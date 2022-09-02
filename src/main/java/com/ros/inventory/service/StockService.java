package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.controller.dto.AddStockDto;
import com.ros.inventory.controller.dto.EditWastageDto;
import com.ros.inventory.controller.dto.WastageDto;
import com.ros.inventory.exception.SiteTransferNotFoundException;
import com.ros.inventory.exception.StockAlreadyClosedException;
import com.ros.inventory.exception.StockAlreadyExistsException;
import com.ros.inventory.exception.StockNotClosedException;
import com.ros.inventory.exception.StockNotFoundException;
import com.ros.inventory.exception.WastageNotFoundException;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.purchaseorder.SiteTransfer;
import com.ros.inventory.controller.dto.ClosedStockDto;
import com.ros.inventory.controller.dto.StockPurchaseOrderDto;
import com.ros.inventory.exception.ClosedStockNotfoundException;
import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.exception.StockEmptyException;
import com.ros.inventory.model.stock.Stock;

public interface StockService {

	public AddStockDto insertStock(AddStockDto stockDto) throws StockAlreadyExistsException;

	public WastageDto insertWastage(WastageDto wastage, UUID stockId) throws StockNotFoundException;

	public AddStockDto insertClosedStock(UUID stockId) throws StockAlreadyClosedException, StockNotFoundException;

	public List<EditWastageDto> editClosedStock(UUID stockId, List<EditWastageDto> wastageList)
			throws StockNotClosedException, StockNotFoundException;

	public List<SiteTransfer> fetchSiteTransfers(UUID stockId) throws StockNotFoundException, SiteTransferNotFoundException;

	public List<WastageDto> fetchWastages(UUID StockId) throws StockNotFoundException, WastageNotFoundException;
	
	public List<ClosedStockDto> findClosedStock() throws StockEmptyException;

	public void deleteWasteProduct(UUID wastageId) throws WastageNotFoundException;

	public Stock aproveStock(UUID stockId) throws ClosedStockNotfoundException;

	public List<StockPurchaseOrderDto> findPurchasedOrderList() throws PurchaseOrderNotFoundException;

	public PurchaseOrder demoViewPurchaseOrder(UUID poId) throws   PurchaseOrderNotFoundException;

	public List<Stock> bulkApproveStock();
	
	public Stock approveStock(UUID stockId) throws StockNotFoundException;
	
	public PurchaseOrder viewSiteTransfer(UUID id) throws PurchaseOrderNotFoundException;	
}
