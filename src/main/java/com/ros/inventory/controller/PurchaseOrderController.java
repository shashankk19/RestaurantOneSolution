package com.ros.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.controller.dto.PurchasedProductDto;
import com.ros.inventory.exception.InCorrectStatusOFOrderException;
import com.ros.inventory.exception.ItsAlreadySubmittedException;
import com.ros.inventory.exception.PurchaseOrderInvalidException;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.service.PurchaseOrderService;
import com.ros.inventory.controller.dto.PurchaseOrderDto;
import com.ros.inventory.exception.EmptyProductsListException;
import com.ros.inventory.exception.PurchaseOrderAlreadyExistsException;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;
import io.swagger.v3.oas.annotations.Operation;

//Controllers

@RestController
@CrossOrigin("*")
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderService;

	@PutMapping("/rejectToSubmit/{id}")
	public ResponseEntity<?> rejectToSubmit(@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<PurchaseOrderDto>(purchaseOrderService.reSubmitRejectedOrder(id),
					HttpStatus.OK);
		} catch (InCorrectStatusOFOrderException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/submit/{poId}")
	@Operation(summary = "To get PurchaseOrders")
	public ResponseEntity<?> putSubmitDraft(@PathVariable(value = "poId") UUID poId) {

		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<PurchaseOrder>(purchaseOrderService.submitDraft(poId), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/rejectAll")
	public ResponseEntity<?> rejectAll() {
		ResponseEntity<?> response = null;
		response = new ResponseEntity<List<PurchaseOrderDto>>(purchaseOrderService.bulkRejectPurchaseOrders(),
				HttpStatus.OK);
		return response;
	}

	@PutMapping("/reject/{id}")
	public ResponseEntity<?> rejectSubmittedOrder(@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<PurchaseOrderDto>(purchaseOrderService.rejectSubmittedPurchaseOrder(id),
					HttpStatus.OK);
		} catch (InCorrectStatusOFOrderException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/bulk/submit/")
	@Operation(summary = "To get Purchase Orders")
	public ResponseEntity<?> put() {

		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<PurchaseOrder>>(purchaseOrderService.getsubmitteddrafts(),
					HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/approveToSubmit/{id}")
	public ResponseEntity<?> approveSubmittedOrder(@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<PurchaseOrderDto>(purchaseOrderService.approveSubmittedOrder(id),
					HttpStatus.OK);
		} catch (InCorrectStatusOFOrderException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editPurchaseOrder(@RequestBody List<PurchasedProductDto> purchasedproducts,
			@PathVariable(value = "id") UUID id) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<PurchasedProductDto>>(
					purchaseOrderService.editPurchaseOrder(purchasedproducts, id), HttpStatus.OK);
		} catch (ItsAlreadySubmittedException | PurchaseOrderInvalidException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/addComment/{id}/{message}")
	public ResponseEntity<?> addComment(@PathVariable(value = "id") UUID id,
			@PathVariable(value = "message") String message) {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<Boolean>(purchaseOrderService.addReasonForRejection(id, message),
					HttpStatus.OK);
		} catch (InCorrectStatusOFOrderException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/view drafts")
	public ResponseEntity<?> getDrafts() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(purchaseOrderService.findDrafts(), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/view submitted")
	public ResponseEntity<?> getSubmittedPurchasedOrders() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(purchaseOrderService.findSubmittedPurchaseOrders(), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;

	}

	@GetMapping("/view Approved")
	public ResponseEntity<?> getApprovedPurchaseOrders() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(purchaseOrderService.findApprovedPurchaseOrders(), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;

	}

	@GetMapping("/view rejected")
	public ResponseEntity<?> getRejectedPurchaseOrders() {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(purchaseOrderService.findRejectedPurchaseOrders(), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") UUID id) throws PurchaseOrderNotFoundException {
		ResponseEntity<?> response = null;
		try {

			response = new ResponseEntity<>(purchaseOrderService.deleteById(id), HttpStatus.OK);

		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;

	}

	@DeleteMapping("/deleteAll/")
	public ResponseEntity<?> deleteAllDrafts() {
		ResponseEntity<?> response = null;
		try {

			response = new ResponseEntity<>(purchaseOrderService.deleteAllDrafts(), HttpStatus.OK);

		} catch (PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);

		}

		return response;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody PurchaseOrder purchaseorder) {
		ResponseEntity<?> response = null;

		response = new ResponseEntity<>(purchaseOrderService.add(purchaseorder), HttpStatus.OK);

		return response;

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePurchaseOrder(@PathVariable(value = "id") UUID id)
			throws PurchaseOrderNotFoundException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(purchaseOrderService.updatePurchaseOrder(id), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;

	}

	@PostMapping("/createorder-and-submit")
	public ResponseEntity<?> addPurchaseOrderAndSubmit(@RequestBody PurchaseOrderDto purchaseorder) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<PurchaseOrder>(purchaseOrderService.createOrderAndSubmit(purchaseorder),
					HttpStatus.OK);
		} catch (PurchaseOrderAlreadyExistsException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@PostMapping("/createorder-and-draft")
	public ResponseEntity<?> addPurchaseOrderAndDraft(@RequestBody PurchaseOrderDto purchaseorder) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<PurchaseOrder>(purchaseOrderService.createOrderAndDraft(purchaseorder),
					HttpStatus.OK);
		} catch (PurchaseOrderAlreadyExistsException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/draftdetails/{poid}")
	public ResponseEntity<?> viewdraftproducts(@PathVariable(value = "poid") UUID id) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<List<PurchasedProduct>>(purchaseOrderService.viewDetailsOfDraft(id),
					HttpStatus.OK);
		} catch (EmptyProductsListException | PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/submitdetails/{poid}")
	public ResponseEntity<?> viewsubmitproducts(@PathVariable(value = "poid") UUID id) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<List<PurchasedProduct>>(purchaseOrderService.viewDetailsOfSubmit(id),
					HttpStatus.OK);
		} catch (EmptyProductsListException | PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/approvedetails/{poid}")
	public ResponseEntity<?> viewapproveproducts(@PathVariable(value = "poid") UUID id) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<List<PurchasedProduct>>(purchaseOrderService.viewDetailsOfApprove(id),
					HttpStatus.OK);
		} catch (EmptyProductsListException | PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@PutMapping("/approve/{poid}")
	public ResponseEntity<?> approveorder(@PathVariable(value = "poid") UUID id) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<PurchaseOrder>(purchaseOrderService.approve(id), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@PutMapping("/approveall")
	public ResponseEntity<?> approveall() {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<List<PurchaseOrder>>(purchaseOrderService.approveAll(), HttpStatus.OK);
		} catch (PurchaseOrderNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/detailsOfReject/{poid}")
	public ResponseEntity<?> viewrejectedproducts(@PathVariable(value = "poid") UUID id) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<List<PurchasedProduct>>(purchaseOrderService.viewDetailsOfReject(id),
					HttpStatus.OK);
		} catch (EmptyProductsListException | PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/ApprovedDetails/{poid}")
	public ResponseEntity<?> viewapprovedproducts(@PathVariable(value = "poid") UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<PurchasedProduct>>(purchaseOrderService.viewDetailsOfApproved(id),
					HttpStatus.OK);
		} catch (EmptyProductsListException | PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@PutMapping("/ApprovedToReceived/{poid}")
	public ResponseEntity<?> updateStatus(@PathVariable(value = "poid") UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<PurchasedProduct>>(purchaseOrderService.updateStatus(id), HttpStatus.OK);
		} catch (EmptyProductsListException | PurchaseOrderNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

}
