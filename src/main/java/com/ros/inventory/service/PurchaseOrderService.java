package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.controller.dto.PurchasedProductDto;
import com.ros.inventory.exception.InCorrectStatusOFOrderException;
import com.ros.inventory.exception.ItsAlreadySubmittedException;
import com.ros.inventory.exception.PurchaseOrderInvalidException;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

import com.ros.inventory.controller.dto.PurchaseOrderApprovedDto;
import com.ros.inventory.controller.dto.PurchaseOrderDraftDto;
import com.ros.inventory.controller.dto.PurchaseOrderRejectedDto;
import com.ros.inventory.controller.dto.PurchaseOrderSubmittedDto;
import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.controller.dto.PurchaseOrderDto;
import com.ros.inventory.exception.EmptyProductsListException;
import com.ros.inventory.exception.PurchaseOrderAlreadyExistsException;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;

public interface PurchaseOrderService {
    //Resubmit a Rejected order
	public PurchaseOrderDto reSubmitRejectedOrder(UUID purchaseOrderId) throws InCorrectStatusOFOrderException;

	// Edit a purchase order from the detailed view screen of purchase order
	List<PurchasedProductDto> editPurchaseOrder(List<PurchasedProductDto> purchasedProductDtos, UUID purchaseOrderId)
			throws PurchaseOrderInvalidException, ItsAlreadySubmittedException;

	// Bulk Reject purchase orders from submitted list/table
	List<PurchaseOrderDto> bulkRejectPurchaseOrders();

	// Reject an individual purchase order from the detailed view screen of a
	// submitted purchase order
	PurchaseOrderDto rejectSubmittedPurchaseOrder(UUID purchaseOrderid) throws InCorrectStatusOFOrderException;

	// Add Reason for rejection
	boolean addReasonForRejection(UUID purchaseOrderId, String message) throws InCorrectStatusOFOrderException;

	// Approve an individual purchase order from submitted list/table
	public PurchaseOrderDto approveSubmittedOrder(UUID purchaseOrderId) throws InCorrectStatusOFOrderException;

	PurchaseOrder add(PurchaseOrder purchaseorder);

	boolean deleteById(UUID purchaseOrderId) throws PurchaseOrderNotFoundException;

	boolean deleteAllDrafts() throws PurchaseOrderNotFoundException;

	PurchaseOrder updatePurchaseOrder(UUID purchaseOrderId) throws PurchaseOrderNotFoundException;

	List<PurchaseOrderDraftDto> findDrafts() throws PurchaseOrderNotFoundException;

	List<PurchaseOrderSubmittedDto> findSubmittedPurchaseOrders() throws PurchaseOrderNotFoundException;

	List<PurchaseOrderApprovedDto> findApprovedPurchaseOrders() throws PurchaseOrderNotFoundException;

	List<PurchaseOrderRejectedDto> findRejectedPurchaseOrders() throws PurchaseOrderNotFoundException;
	
	public PurchaseOrder createOrderAndSubmit(PurchaseOrderDto purchaseorder) throws PurchaseOrderAlreadyExistsException;
	
	public PurchaseOrder createOrderAndDraft(PurchaseOrderDto purchaseorder) throws PurchaseOrderAlreadyExistsException;

	public List<PurchasedProduct> viewDetailsOfDraft(UUID id) throws EmptyProductsListException, PurchaseOrderNotFoundException;

	public List<PurchasedProduct> viewDetailsOfSubmit(UUID id) throws EmptyProductsListException, PurchaseOrderNotFoundException;

	public List<PurchasedProduct> viewDetailsOfApprove(UUID id) throws EmptyProductsListException, PurchaseOrderNotFoundException;

	public PurchaseOrder approve(UUID id) throws PurchaseOrderNotFoundException;
	
	public List<PurchaseOrder> approveAll() throws PurchaseOrderNotFoundException;

	public List<PurchasedProduct> viewDetailsOfReject(UUID id) throws EmptyProductsListException, PurchaseOrderNotFoundException;
	
	public List<PurchasedProduct> viewDetailsOfApproved(UUID id) throws EmptyProductsListException, PurchaseOrderNotFoundException;

	public List<PurchasedProduct> updateStatus(UUID id) throws EmptyProductsListException, PurchaseOrderNotFoundException;
	
	PurchaseOrder submitDraft(UUID poId) throws PurchaseOrderNotFoundException;
	
	List<PurchaseOrder> getsubmitteddrafts() throws PurchaseOrderNotFoundException;
	
	List<PurchaseOrder> getDrafts(String purchaseOrderType) throws PurchaseOrderNotFoundException;

}
