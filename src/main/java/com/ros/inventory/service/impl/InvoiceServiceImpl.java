package com.ros.inventory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.exception.InvoiceNotFoundException;
import com.ros.inventory.mapper.InvoiceMapper;
import com.ros.inventory.model.invoice.Invoice;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.repository.InvoiceRepository;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Autowired 
	private  InvoiceMapper invoiceMapper;
	
	
	public List<InvoiceDto> getMonthlyInvoice(int year, int month) {
		
		List<Invoice> invoce=invoiceRepository.findAll();
		List<InvoiceDto> invoiceDto=new ArrayList<InvoiceDto>();
		for(Invoice i:invoce) {
			
			if(i.getInvoiceDate().getYear()==year) {
				
				if(i.getInvoiceDate().getMonthValue()==month) {
						
					PurchaseOrder purchasorder=purchaseOrderRepository.findById(i.getPurchase().getPoId()).get();
					InvoiceDto invoicedto=invoiceMapper.convertToInvoiceDto(purchasorder);
					invoiceDto.add(invoicedto);
				}
			}
		}
		return invoiceDto;
	
	}
	
	public InvoiceDto viewInvoice(UUID invoiceId) throws InvoiceNotFoundException{
		
		if(!invoiceRepository.existsById(invoiceId)) {
			throw new InvoiceNotFoundException("Invoice not Found");
		}
		Invoice i = invoiceRepository.findById(invoiceId).get();
		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(i.getPurchase().getPoId()).get();
		InvoiceDto voice = invoiceMapper.convertToInvoiceDto(purchaseOrder);
		return voice;
		
	}
	
	public List<InvoiceDto> getYearlyInvoice(int year) throws InvoiceNotFoundException {
		
		List<Invoice> invoices = invoiceRepository.findAll();
		List<InvoiceDto> invoiceList = new ArrayList<InvoiceDto>();
		if(invoices.isEmpty()) {
			throw new InvoiceNotFoundException("Invoice not Found");
		}
		for(Invoice i : invoices) {
			if(i.getInvoiceDate().getYear() == year) {
				PurchaseOrder order = purchaseOrderRepository.findById(i.getPurchase().getPoId()).get();
				InvoiceDto invoice = invoiceMapper.convertToInvoiceDto(order);
				invoiceList.add(invoice);
			}
		}
		return invoiceList;
	}
}
