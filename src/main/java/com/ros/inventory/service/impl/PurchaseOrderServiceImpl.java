package com.ros.inventory.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.controller.dto.PurchasedProductDto;
import com.ros.inventory.exception.EmptyProductsListException;
import com.ros.inventory.exception.InCorrectStatusOFOrderException;
import com.ros.inventory.exception.ItsAlreadySubmittedException;
import com.ros.inventory.exception.PurchaseOrderAlreadyExistsException;
import com.ros.inventory.exception.PurchaseOrderInvalidException;
import com.ros.inventory.mapper.PurchasedProductMapper;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.repository.PurchasedProductRepository;
import com.ros.inventory.service.PurchaseOrderService;
import com.ros.inventory.controller.dto.PurchaseOrderApprovedDto;
import com.ros.inventory.controller.dto.PurchaseOrderDraftDto;
import com.ros.inventory.controller.dto.PurchaseOrderDto;
import com.ros.inventory.controller.dto.PurchaseOrderRejectedDto;
import com.ros.inventory.controller.dto.PurchaseOrderSubmittedDto;
import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.mapper.PurchaseOrderMapper;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PurchasedProductRepository purchasedProductRepository;

	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;

	@Autowired
	private PurchasedProductMapper purchasedProductMapper;
	
	//Resubmit a Rejected order
	@Override
	public PurchaseOrderDto reSubmitRejectedOrder(UUID purchaseOrderId) throws InCorrectStatusOFOrderException {

		PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId).get();

		PurchaseOrderType type = order.getPurchaseOrderType();

		if (!(type == PurchaseOrderType.REJECTED)) {

			throw new InCorrectStatusOFOrderException("This order is not rejected");

		} else {

			order.setPurchaseOrderType(PurchaseOrderType.SUBMITTED);

			purchaseOrderRepository.save(order);
			
			PurchaseOrderDto dto = purchaseOrderMapper.convertToPurchaseOrderDto(order);

			return dto;
		}
	}
	
	// Edit a purchase order from the detailed view screen of purchase order
	@Override
	public List<PurchasedProductDto> editPurchaseOrder(List<PurchasedProductDto> purchasedProductDtos,
			UUID purchaseOrderId) throws PurchaseOrderInvalidException, ItsAlreadySubmittedException {

		PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId).get();

		List<PurchasedProductDto> results = new ArrayList<PurchasedProductDto>();

		if (order == null) {
			throw new PurchaseOrderInvalidException("Invalid purchase order");

		} else if (!(order.getPurchaseOrderType() == PurchaseOrderType.DRAFT)) {

			throw new ItsAlreadySubmittedException("PurchaseOrder is already submitted");
		}

		for (PurchasedProduct purchasedProduct : order.getProducts()) {

			for (PurchasedProductDto purchaseProductDto : purchasedProductDtos) {

				if (purchasedProduct.getId().equals(purchaseProductDto.getProductId())) {

					purchasedProduct.setQuantity(purchaseProductDto.getQuantity());

					purchasedProductRepository.save(purchasedProduct);
				}
			}
		}

		purchaseOrderRepository.save(order);

		for (PurchasedProduct product : order.getProducts()) {

			PurchasedProductDto dto = purchasedProductMapper.convertToPurchasedProductDto(product);

			results.add(dto);
		}
		return results;
	}
	
	// Bulk Reject purchase orders from submitted list/table
	@Override
	public List<PurchaseOrderDto> bulkRejectPurchaseOrders() {

		List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		
		List<PurchaseOrderDto> purchaseOrderdtoList = new ArrayList<>();

		for (PurchaseOrder purchaseOrder : orders) {

			if (purchaseOrder.getPurchaseOrderType() == PurchaseOrderType.SUBMITTED) {

				purchaseOrder.setPurchaseOrderType(PurchaseOrderType.REJECTED);

				purchaseOrder.setReason("Change of Plans");

				purchaseOrder.setComment("We dont need some of the listed items for the evening");

				Date date = new Date();

				purchaseOrder.setRejectionDate(date);

				purchaseOrderRepository.save(purchaseOrder);
				
				PurchaseOrderDto dto = purchaseOrderMapper.convertToPurchaseOrderDto(purchaseOrder);
				purchaseOrderdtoList.add(dto);
				}
		}
		return purchaseOrderdtoList;
	}
	
	
	@Override
	public PurchaseOrder submitDraft(UUID poId) throws PurchaseOrderNotFoundException {
		
		PurchaseOrder order = purchaseOrderRepository.findById(poId).get();
		if(!(order.getPurchaseOrderType().equals(PurchaseOrderType.DRAFT))) {
			throw new PurchaseOrderNotFoundException("Purchase Order Not Found");
		}
		order.setPurchaseOrderType(PurchaseOrderType.SUBMITTED);
		purchaseOrderRepository.save(order);
		return order;
	}

	@Override
	public List<PurchaseOrder> getsubmitteddrafts() throws PurchaseOrderNotFoundException {
		
		List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		if(orders.isEmpty()) {
			throw new PurchaseOrderNotFoundException("Purchase Orders Empty");
		}
		
		for(PurchaseOrder o : orders) {
			if((o.getPurchaseOrderType().equals(PurchaseOrderType.DRAFT))) {
				o.setPurchaseOrderType(PurchaseOrderType.SUBMITTED);
				purchaseOrderRepository.save(o);
					
			}
		}
		return orders;
	}
	
	// Reject an individual purchase order from the detailed view screen of a
	// submitted purchase order
	@Override
	public PurchaseOrderDto rejectSubmittedPurchaseOrder(UUID purchaseOrderid) throws InCorrectStatusOFOrderException {

		PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderid).get();

		PurchaseOrderType type = order.getPurchaseOrderType();

		if (!(type == PurchaseOrderType.SUBMITTED)) {

			throw new InCorrectStatusOFOrderException("This order is not submitted");
		}
		order.setPurchaseOrderType(PurchaseOrderType.REJECTED);

		order.setReason("Change of Plans");

		order.setComment("We dont need some of the listed items for the evening");

		Date date = new Date();

		order.setRejectionDate(date);

		purchaseOrderRepository.save(order);
		
		PurchaseOrderDto dto = purchaseOrderMapper.convertToPurchaseOrderDto(order);

		return dto;
	}
	// Add Reason for rejection
	@Override
	public boolean addReasonForRejection(UUID purchaseOrderId, String message) throws InCorrectStatusOFOrderException {

		PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId).get();

		PurchaseOrderType type = order.getPurchaseOrderType();

		if (!(type == PurchaseOrderType.REJECTED)) {

			throw new InCorrectStatusOFOrderException("This order is not Rejected");
		}
		order.setReason(message);

		order.setComment("We dont need some of the listed items for the evening");

		purchaseOrderRepository.save(order);

		return true;
	}
	// Approve an individual purchase order from submitted list/table
	@Override
	public PurchaseOrderDto approveSubmittedOrder(UUID purchaseOrderId) throws InCorrectStatusOFOrderException {

		PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId).get();

		PurchaseOrderType type = order.getPurchaseOrderType();

		if (!(type == PurchaseOrderType.SUBMITTED)) {

			throw new InCorrectStatusOFOrderException("This order is not submitted");
		}
		order.setPurchaseOrderType(PurchaseOrderType.APPROVED);

		Date date = new Date();

		order.setApprovedDate(date);

		purchaseOrderRepository.save(order);
		
		PurchaseOrderDto dto = purchaseOrderMapper.convertToPurchaseOrderDto(order);
		return dto;

		
	}

	@Override
	public PurchaseOrder add(PurchaseOrder purchaseorder) {

		return purchaseOrderRepository.save(purchaseorder);
	}

	@Override
	public boolean deleteById(UUID purchaseOrderId) throws PurchaseOrderNotFoundException {

		boolean isDeleted = false;

		PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(purchaseOrderId).get();

		if (!(purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.DRAFT))) {

			throw new PurchaseOrderNotFoundException("Type is not DRAFT");
		}
		purchaseOrderRepository.delete(purchaseOrder);

		isDeleted = true;

		return isDeleted;

	}

	@Override
	public boolean deleteAllDrafts() throws PurchaseOrderNotFoundException {

		boolean isDeleted = false;

		List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

		if (purchaseOrders.isEmpty()) {

			throw new PurchaseOrderNotFoundException("No Drafts");

		} else {

			for (PurchaseOrder purchaseOrder : purchaseOrders) {
				if (purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.DRAFT)) {
					purchaseOrderRepository.delete(purchaseOrder);
				}
			}
			isDeleted = true;
			return isDeleted;
		}
	}

	@Override
	public List<PurchaseOrderDraftDto> findDrafts() throws PurchaseOrderNotFoundException {

		List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

		List<PurchaseOrderDraftDto> drafts = new ArrayList<PurchaseOrderDraftDto>();

		if (purchaseOrders.isEmpty()) {
			throw new PurchaseOrderNotFoundException();
		}
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if (purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.DRAFT)) {
				PurchaseOrderDraftDto dto = purchaseOrderMapper.convertToDraftDto(purchaseOrder);
				drafts.add(dto);

			}
		}
		if (drafts.isEmpty()) {
			throw new PurchaseOrderNotFoundException("There are no draft purchase orders");

		}
		return drafts;
	}

	@Override
	public List<PurchaseOrderSubmittedDto> findSubmittedPurchaseOrders() throws PurchaseOrderNotFoundException {
		List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

		List<PurchaseOrderSubmittedDto> submitted = new ArrayList<PurchaseOrderSubmittedDto>();

		if (purchaseOrders.isEmpty()) {

			throw new PurchaseOrderNotFoundException();
		}
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if (purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.SUBMITTED)) {
				PurchaseOrderSubmittedDto dto = purchaseOrderMapper.convertToSubmittedDto(purchaseOrder);
				submitted.add(dto);

			}
		}
		if (submitted.isEmpty()) {
			throw new PurchaseOrderNotFoundException("There are no submitted purchase orders");
		}
		return submitted;
	}

	@Override
	public List<PurchaseOrderApprovedDto> findApprovedPurchaseOrders() throws PurchaseOrderNotFoundException {

		List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

		List<PurchaseOrderApprovedDto> approved = new ArrayList<PurchaseOrderApprovedDto>();

		if (purchaseOrders.isEmpty()) {

			throw new PurchaseOrderNotFoundException();
		}
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if (purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.APPROVED)) {
				PurchaseOrderApprovedDto dto = purchaseOrderMapper.convertToApprovedDto(purchaseOrder);
				approved.add(dto);

			}

		}
		if (approved.isEmpty()) {
			throw new PurchaseOrderNotFoundException("There are no approved Purchase Orders");
		}
		return approved;

	}

	@Override
	public List<PurchaseOrderRejectedDto> findRejectedPurchaseOrders() throws PurchaseOrderNotFoundException {

		List<PurchaseOrderRejectedDto> rejected = new ArrayList<PurchaseOrderRejectedDto>();

		List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();

		if (purchaseOrders.isEmpty()) {
			throw new PurchaseOrderNotFoundException();
		}
		for (PurchaseOrder purchaseOrder : purchaseOrders) {
			if (purchaseOrder.getPurchaseOrderType().equals(PurchaseOrderType.REJECTED)) {
				PurchaseOrderRejectedDto dto = purchaseOrderMapper.convertToRejectedDto(purchaseOrder);
				rejected.add(dto);

			}

		}
		if (rejected.isEmpty()) {
			throw new PurchaseOrderNotFoundException("There are no rejected Purchase Orders");
		}
		return rejected;

	}

	@Override
	public PurchaseOrder updatePurchaseOrder(UUID purchaseOrderId) throws PurchaseOrderNotFoundException {

		PurchaseOrder purchaseOrderSubmitted = purchaseOrderRepository.findById(purchaseOrderId).get();

		if (!purchaseOrderSubmitted.getPurchaseOrderType().equals(PurchaseOrderType.SUBMITTED)) {

			throw new PurchaseOrderNotFoundException("It is not a submitted Purchase Order");

		} else {

			purchaseOrderSubmitted.setPurchaseOrderType(PurchaseOrderType.REJECTED);

			purchaseOrderRepository.save(purchaseOrderSubmitted);
		}
		return purchaseOrderSubmitted;
	}

	public PurchaseOrder createOrderAndSubmit(PurchaseOrderDto purchaseorder)
			throws PurchaseOrderAlreadyExistsException {
		if (purchaseOrderRepository.existsById(purchaseorder.getPoId())) {
			throw new PurchaseOrderAlreadyExistsException("Purchase order exists by this id");
		}
		purchasedProductRepository.saveAll(purchaseorder.getProducts());
		PurchaseOrder poorder = new PurchaseOrder();
		PurchaseOrder order = purchaseOrderMapper.convertToPurchaseOrderEntity(purchaseorder, poorder);
		order.setPurchaseOrderType(PurchaseOrderType.SUBMITTED);
		order.setPurchaseOrderDate(java.sql.Date.valueOf(LocalDate.now()));
		purchaseOrderRepository.save(order);
		return order;
	}

	@Override
	public PurchaseOrder createOrderAndDraft(PurchaseOrderDto purchaseorder)
			throws PurchaseOrderAlreadyExistsException {
		if (purchaseOrderRepository.existsById(purchaseorder.getPoId())) {
			throw new PurchaseOrderAlreadyExistsException("Purchase order exists by this id");
		}

		PurchaseOrder poorder = new PurchaseOrder();
		PurchaseOrder order = purchaseOrderMapper.convertToPurchaseOrderEntity(purchaseorder, poorder);
		order.setPurchaseOrderType(PurchaseOrderType.DRAFT);
		order.setPurchaseOrderDate(java.sql.Date.valueOf(LocalDate.now()));
		purchaseOrderRepository.save(order);
		return order;
	}

	@Override
	public List<PurchasedProduct> viewDetailsOfDraft(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {

		List<PurchasedProduct> draftProductsList = new ArrayList<PurchasedProduct>();
		if (purchaseOrderRepository.existsById(id)
				&& purchaseOrderRepository.findById(id).get().getPurchaseOrderType() == PurchaseOrderType.DRAFT) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(id);
			draftProductsList = purchaseorder.getProducts();
			if (!(draftProductsList == null)) {
				return draftProductsList;
			} else {
				throw new EmptyProductsListException("No products it the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}

	}

	@Override
	public List<PurchasedProduct> viewDetailsOfSubmit(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {

		List<PurchasedProduct> draftProductsList = new ArrayList<PurchasedProduct>();
		if (purchaseOrderRepository.existsById(id)
				&& purchaseOrderRepository.findById(id).get().getPurchaseOrderType() == PurchaseOrderType.SUBMITTED) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(id);
			draftProductsList = purchaseorder.getProducts();
			if (!(draftProductsList == null)) {
				return draftProductsList;
			} else {
				throw new EmptyProductsListException("No products it the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}
	}

	@Override
	public List<PurchasedProduct> viewDetailsOfApprove(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {

		List<PurchasedProduct> draftProductsList = new ArrayList<PurchasedProduct>();
		if (purchaseOrderRepository.existsById(id)
				&& purchaseOrderRepository.findById(id).get().getPurchaseOrderType() == PurchaseOrderType.APPROVED) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(id);
			draftProductsList = purchaseorder.getProducts();
			if (!(draftProductsList == null)) {
				return draftProductsList;
			} else {
				throw new EmptyProductsListException("No products it the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}
	}

	@Override
	public PurchaseOrder approve(UUID id) throws PurchaseOrderNotFoundException {

		PurchaseOrder order = purchaseOrderRepository.findById(id).get();
		if (!(order.getPurchaseOrderType().equals(PurchaseOrderType.SUBMITTED))) {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}
		order.setPurchaseOrderType(PurchaseOrderType.APPROVED);
		order.setApprovedDate(java.sql.Date.valueOf(LocalDate.now()));
		purchaseOrderRepository.save(order);
		return order;
	}

	@Override
	public List<PurchaseOrder> approveAll() throws PurchaseOrderNotFoundException {

		List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
		List<PurchaseOrder> orders1 = new ArrayList<PurchaseOrder>();
		if (orders.isEmpty()) {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}

		for (PurchaseOrder o : orders) {
			if (o.getPurchaseOrderType().equals(PurchaseOrderType.SUBMITTED)) {
				o.setPurchaseOrderType(PurchaseOrderType.APPROVED);
				o.setApprovedDate(java.sql.Date.valueOf(LocalDate.now()));
				purchaseOrderRepository.save(o);
				orders1.add(o);

			}
		}

		return orders1;
	}

	@Override
	public List<PurchasedProduct> viewDetailsOfReject(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {

		List<PurchasedProduct> rejectProductsList = new ArrayList<PurchasedProduct>();
		if (purchaseOrderRepository.findById(id).get().getPurchaseOrderType() == PurchaseOrderType.REJECTED) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(id);
			rejectProductsList = purchaseorder.getProducts();
			if (!(rejectProductsList.isEmpty())) {
				return rejectProductsList;
			} else {
				throw new EmptyProductsListException("No products in the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}
	}

	public List<PurchasedProduct> viewDetailsOfApproved(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {

		List<PurchasedProduct> approvedProductsList = new ArrayList<PurchasedProduct>();
		if (purchaseOrderRepository.findById(id).get().getPurchaseOrderType() == PurchaseOrderType.APPROVED) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(id);
			approvedProductsList = purchaseorder.getProducts();
			if (!(approvedProductsList.isEmpty())) {
				return approvedProductsList;
			} else {
				throw new EmptyProductsListException("No products in the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}
	}

	@Override
	public List<PurchasedProduct> updateStatus(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {

		List<PurchasedProduct> approvedProductsList = new ArrayList<PurchasedProduct>();
		if (purchaseOrderRepository.findById(id).get().getPurchaseOrderType() == PurchaseOrderType.APPROVED) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(id);
			purchaseorder.setPurchaseOrderType(PurchaseOrderType.RECEIVED);
			purchaseOrderRepository.save(purchaseorder);
			approvedProductsList = purchaseorder.getProducts();
			if (!(approvedProductsList.isEmpty())) {
				return approvedProductsList;
			} else {
				throw new EmptyProductsListException("No products in the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("purchase order does not exists");
		}
	}
	public List<PurchaseOrder> getDrafts(String purchaseOrderType) throws PurchaseOrderNotFoundException {
		
		List<PurchaseOrder> purchaseDrafts=purchaseOrderRepository.getDrafts(purchaseOrderType);
		if(purchaseDrafts.isEmpty()) {
			throw new PurchaseOrderNotFoundException();
		}
		
		return purchaseDrafts;	
	}

}
