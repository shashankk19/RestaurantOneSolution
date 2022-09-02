package com.ros.inventory.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.controller.dto.supplier.ExternalSupplierDto;
import com.ros.inventory.controller.dto.supplier.InternalSupplierDto;
import com.ros.inventory.controller.dto.supplier.ProductMasterDto;
import com.ros.inventory.exception.SupplierAlreadyExistsException;
import com.ros.inventory.exception.SupplierNotFoundException;
import com.ros.inventory.service.SupplierService;
import com.ros.inventory.exception.ProductNotFoundException;
import com.ros.inventory.exception.RestaurantNotFoundException;
import com.ros.inventory.model.supplier.BankDetails;
import com.ros.inventory.model.supplier.Supplier;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/supplier")
@CrossOrigin("*")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@Operation(description = "Adding External Supplier")
	@PostMapping("/external/{restaurandId}")
	public ResponseEntity<?> postSupplier(@RequestBody ExternalSupplierDto externalSupplierDto,
			@PathVariable(value = "restaurandId") UUID restaurantId) {

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<>(supplierService.addSupplier(externalSupplierDto, restaurantId),
					HttpStatus.OK);

		} catch(SupplierAlreadyExistsException | RestaurantNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@Operation(description = "Adding Internal Supplier")
	@PostMapping("/internal/{restaurandId}")
	public ResponseEntity<?> postSupplier(@RequestBody InternalSupplierDto internalSupplierDto,
			@PathVariable(value = "restaurandId") UUID restaurantId) {

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<>(supplierService.addSupplier(internalSupplierDto, restaurantId),
					HttpStatus.OK);

		} catch (SupplierAlreadyExistsException | RestaurantNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@Operation(description = "Editing Supplier")
	@PutMapping("/")
	public ResponseEntity<?> putSupplier(@RequestBody SupplierDto supplierDto) {
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(supplierService.editSupplier(supplierDto), HttpStatus.OK);

		} catch (SupplierNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@Operation(description = "Editing Product Master")
	@PutMapping("/{supplierId}/products")
	public ResponseEntity<?> putProductMasters(@PathVariable(value = "supplierId") UUID supplierId,
			@RequestBody List<ProductMasterDto> productMasterDto) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(supplierService.updateProductMaster(supplierId, productMasterDto),
					HttpStatus.OK);

		} catch (SupplierNotFoundException e) {

			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping("/viewProfilePic/{supplierId}")
	public ResponseEntity<?> getProfilePic(@PathVariable(value = "supplierId") UUID supplierId)
			throws SupplierNotFoundException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<String>(supplierService.getSupplierProfile(supplierId), HttpStatus.OK);
		} catch (SupplierNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@GetMapping("/viewSuppliersBankDetails/{supplierId}")
	public ResponseEntity<?> viewSuppliersBankDetails(@PathVariable(value = "supplierId") UUID supplierId)
			throws SupplierNotFoundException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<BankDetails>(supplierService.viewSupplierBankDetailss(supplierId),
					HttpStatus.OK);
		} catch (SupplierNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@DeleteMapping("/deleteSupplier/{supplierId}")
	public ResponseEntity<?> deleteSupplier(@PathVariable(value = "supplierId") UUID supplierId)
			throws SupplierNotFoundException {
		ResponseEntity<?> response = null;

		response = new ResponseEntity<Supplier>(supplierService.removeSupplier(supplierId), HttpStatus.OK);

		return response;
	}

	@GetMapping("/viewSuppliersProductMasterDetails/{supplierId}")
	public ResponseEntity<?> viewSuppliersProductMaster(@PathVariable(value = "supplierId") UUID supplierId)
			throws SupplierNotFoundException {
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<List<ProductMasterDto>>(supplierService.viewSupplierProductMaster(supplierId),
					HttpStatus.OK);
		} catch (SupplierNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

	@PutMapping("/productmaster")
	public ResponseEntity<?> putProductMaster(@RequestBody ProductMasterDto productMasterDto) {
		ResponseEntity<?> response = null;

		try {
			response = new ResponseEntity<Boolean>(supplierService.updateProductMaster(productMasterDto),
					HttpStatus.OK);
		} catch (SupplierNotFoundException | ProductNotFoundException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}

}
