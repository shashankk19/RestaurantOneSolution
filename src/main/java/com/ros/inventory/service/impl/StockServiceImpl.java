package com.ros.inventory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.controller.dto.AddStockDto;
import com.ros.inventory.controller.dto.EditWastageDto;
import com.ros.inventory.controller.dto.WastageDto;
import com.ros.inventory.exception.SiteTransferNotFoundException;
import com.ros.inventory.exception.StockAlreadyClosedException;
import com.ros.inventory.exception.StockAlreadyExistsException;
import com.ros.inventory.exception.StockNotClosedException;
import com.ros.inventory.exception.StockNotFoundException;
import com.ros.inventory.mapper.StockMapper;
import com.ros.inventory.model.purchaseorder.SiteTransfer;
import com.ros.inventory.model.stock.Stock;
import com.ros.inventory.model.stock.StockType;
import com.ros.inventory.model.stock.Wastage;
import com.ros.inventory.controller.dto.ClosedStockDto;
import com.ros.inventory.controller.dto.StockPurchaseOrderDto;
import com.ros.inventory.exception.ClosedStockNotfoundException;
import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.exception.StockEmptyException;
import com.ros.inventory.exception.WastageNotFoundException;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.repository.SiteTransferRepository;
import com.ros.inventory.repository.StockRepository;
import com.ros.inventory.repository.WastageRepository;
import com.ros.inventory.service.StockService;

@Service
public class StockServiceImpl implements StockService{
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private WastageRepository wastageRepository;
	
	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;

	@Autowired
	private SiteTransferRepository siteTransferRepository;
			
	@Override
	public AddStockDto insertStock(AddStockDto stockDto) throws StockAlreadyExistsException {
		
		if(stockRepository.existsById(stockDto.getStockId())) {
			throw new StockAlreadyExistsException("Stock is already available");
		}
		else {
			Stock stock= new Stock();
			stock.setStartDate(stockDto.getStartDate());
			stock.setEndDate(stockDto.getEndDate());
			stockRepository.save(stock);
			
		}
		return stockDto;
	}

	
	@Override
	public AddStockDto insertClosedStock(UUID stockId) throws StockAlreadyClosedException, StockNotFoundException {
		
		Stock stock = stockRepository.findById(stockId).get();
		
		AddStockDto stockDto = null;
		
		if(stockRepository.existsById(stockId) && !stock.getStockType().equals(StockType.CLOSED)) {
	
				stock.setStockType(StockType.CLOSED);
				
				stockRepository.save(stock);
				
				stockDto = stockMapper.convertToAddStockDto(stock);
		}
		else if(stock.getStockType().equals(StockType.CLOSED)) {
			
			throw new StockAlreadyClosedException("Stock is already closed");
		}
		else {
			
			throw new StockNotFoundException("Stock is not found");
		}

		return stockDto;
	}


	@Override
	public WastageDto insertWastage(WastageDto wastage, UUID stockId) throws StockNotFoundException {
		
		Stock stock = stockRepository.findById(stockId).get();
		
		if(stockRepository.existsById(stockId)) {

			Wastage wastageObj=new Wastage();
			wastageObj.setProduct(wastage.getProduct());
			wastageObj.setPricePerUnit(wastage.getPricePerUnit());
			wastageObj.setQuantity(wastage.getQuantity());
			wastageObj.setStock(stock);
			stock.getWastages().add(wastageObj);
			wastageRepository.saveAndFlush(wastageObj);
			WastageDto result=stockMapper.convertToWastageDto(wastageObj);
			return result;

		}
		else {
			throw new StockNotFoundException("Stock is not found");
		}
	}


	@Override
	public List<EditWastageDto> editClosedStock(UUID stockId, List<EditWastageDto> wastageList) throws StockNotClosedException, StockNotFoundException {
		
		Stock stock = stockRepository.findById(stockId).get();
		
		List<Wastage> wastages = stock.getWastages();
		
		List<EditWastageDto> result=new ArrayList<EditWastageDto>();
		
		if(stockRepository.existsById(stockId) && stock.getStockType().equals(StockType.CLOSED)) {
			for(Wastage wastage1: wastages) {
				for(EditWastageDto wastage2: wastageList) {
					if(wastage1.getWastageId().equals(wastage2.getWastageId())) {
						wastage1.setQuantity(wastage2.getQuantity());
					}
				}
			}
			wastageRepository.saveAll(wastages);
			for(Wastage wastage2:wastages) {
				EditWastageDto editedDto = stockMapper.convertToEditWastageDto(wastage2);
				result.add(editedDto);
			}
			return result;
		}
		else if(!(stock.getStockType().equals(StockType.CLOSED))){
			throw new StockNotClosedException("Stock is not closed");
		}
		else {
			throw new StockNotFoundException("Stock is not found");
		}
	}


	@Override
	public List<SiteTransfer> fetchSiteTransfers(UUID stockId) throws StockNotFoundException, SiteTransferNotFoundException {
		
		Stock stock = stockRepository.findById(stockId).get();
		List<SiteTransfer> siteTransfers = stock.getSiteTransfers();
		
		if(stockRepository.existsById(stockId) && !siteTransfers.isEmpty()) {
			
			return siteTransfers;
		}
		else if(siteTransfers.isEmpty()) {
			throw new SiteTransferNotFoundException("No Site transfers are found");
		}
		else {
			throw new StockNotFoundException("Stock is not found");
		}
	}


	@Override
	public List<WastageDto> fetchWastages(UUID StockId) throws StockNotFoundException, WastageNotFoundException {
		
		Stock s = stockRepository.findById(StockId).get();
		List<Wastage> wastages = s.getWastages();
		
		if(stockRepository.existsById(StockId) && !wastages.isEmpty()) {
			List<WastageDto> result = new ArrayList<WastageDto>();
			
			for(Wastage w: wastages) {
				result.add(stockMapper.convertToWastageDto(w));
			}
			return result;
			
		}
		else if(wastages.isEmpty()) {
			throw new WastageNotFoundException("No wastages");
		}
		else {
			throw new StockNotFoundException("Stock is not found");
		}

	}

	@Override
	public List<ClosedStockDto> findClosedStock() throws StockEmptyException {
		List<ClosedStockDto> closedStocks = new ArrayList<ClosedStockDto>();
		List<Stock> stocks = stockRepository.findAll();
		boolean isEmpty = false;
		if (stocks.isEmpty()) {
			// Whether there are element in the stock table or not
			throw new StockEmptyException("no stocks are present");
		} else {
			for (Stock stock : stocks) {
				if (stock.getStockType().equals(StockType.CLOSED)) {
					isEmpty = true;
					ClosedStockDto dto = stockMapper.convertToClosedStockDto(stock);
					closedStocks.add(dto);
				}
			}
		}
		if (!isEmpty)
			// Whether there are closed stock type is present in stock table or not
			throw new StockEmptyException("no closed Stocks Found");
		return closedStocks;
	}

	@Override
	public void deleteWasteProduct(UUID wastsgeId) throws WastageNotFoundException {
		
		List<Wastage> wasteProducts= null;
		
		
		 wasteProducts= wastageRepository.findAll();
		 if( wasteProducts.isEmpty())
		 {
			 throw new WastageNotFoundException("no wasatge");
		 }
		
		 
		for(Wastage wastage: wasteProducts)
		{
			if(wastage.getWastageId().equals(wastsgeId)){
				wastageRepository.delete(wastage);
			}
			else {
				throw new WastageNotFoundException("No waste id found");
			}
		}	
	}


	
	@Override
	public Stock aproveStock(UUID id) throws ClosedStockNotfoundException  {
		
		List<Stock> stocks=stockRepository.findAll();
		
		if(stocks.isEmpty())
		{
			throw new ClosedStockNotfoundException("stock is empty");
		}
			
		Stock stock=stockRepository.findById(id).get();
		if(stock.equals(null))
		{
			throw new ClosedStockNotfoundException ("stock with given id is not found");
		}
		if(stock.getStockType().equals(StockType.CLOSED)) {
			
			stock.setStockType(StockType.APPROVED);
			stockRepository.save(stock);
		}
		else {
			throw new ClosedStockNotfoundException("no closed Stock found");
		}
		
		return stock;

	}

	@Override
	public List<StockPurchaseOrderDto> findPurchasedOrderList() throws PurchaseOrderNotFoundException {
		List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
		List<StockPurchaseOrderDto> purchaseOrderOpeningDto = new ArrayList<StockPurchaseOrderDto>();
		if (purchaseOrders.isEmpty()) {
			throw new PurchaseOrderNotFoundException("no purchase orders found");
		}
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if (purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.EXPORTED)) {
				StockPurchaseOrderDto dto = purchaseOrderMapper.convertToStockPurchaseOrderDto(purchaseOrder);
				purchaseOrderOpeningDto.add(dto);
			}
		}
		if (purchaseOrderOpeningDto.isEmpty()) {
			throw new PurchaseOrderNotFoundException(" no purchaseorders are  exported");
		}
		return purchaseOrderOpeningDto;
	}

	
	//approvestock
	@Override
	public Stock approveStock(UUID stockId) throws StockNotFoundException {
		Stock stock=stockRepository.findById(stockId).get();
		if(stockRepository.existsById(stockId)) {
			if(stock.getStockType().equals(StockType.CLOSED)){
				stock.setStockType(StockType.APPROVED);
				stockRepository.save(stock);
			}				
		}
		else {
			throw new StockNotFoundException("Stock not Found");
		}
		return stock;
	}
	
	//viewPurchaseorder
	@Override
	public PurchaseOrder demoViewPurchaseOrder(UUID poId) throws PurchaseOrderNotFoundException {
	PurchaseOrder purchaseOrder=null;
	if(purchaseOrderRepository.existsById(poId)) {
		purchaseOrder=purchaseOrderRepository.findById(poId).get();
		return purchaseOrder;
	}
	else {
		throw new PurchaseOrderNotFoundException("Purchase order not found");
	}
}
	
    //bulkapproval of stock
	@Override
	public List<Stock> bulkApproveStock() {
		List<Stock> stocks=stockRepository.findAll();
		for(Stock stock:stocks) {
			if(stock.getStockType().equals(StockType.CLOSED)) {
				stock.setStockType(StockType.APPROVED);
				stockRepository.save(stock);
			}
		}
		return stocks;
	}
	//viewSiteTransfers
	@Override
	public PurchaseOrder viewSiteTransfer(UUID id) throws PurchaseOrderNotFoundException {
		
		PurchaseOrder purchaseOrder=new PurchaseOrder();
	
		if(siteTransferRepository.existsById(id)) {
			UUID Id = siteTransferRepository.findById(id).get().getPurchaseOrderId();
			purchaseOrder=purchaseOrderRepository.findById(Id).get();	
			return purchaseOrder;
		}
		else {
			throw new PurchaseOrderNotFoundException("Purchase order not found");
		}
	}		
}
