package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.controller.dto.SupplierDto;
import com.ros.inventory.controller.dto.supplier.ExternalSupplierDto;
import com.ros.inventory.controller.dto.supplier.InternalSupplierDto;
import com.ros.inventory.controller.dto.supplier.ProductMasterDto;
import com.ros.inventory.exception.RestaurantNotFoundException;
import com.ros.inventory.exception.SupplierAlreadyExistsException;
import com.ros.inventory.exception.SupplierNotFoundException;

import com.ros.inventory.exception.ProductNotFoundException;
import com.ros.inventory.model.supplier.BankDetails;
import com.ros.inventory.model.supplier.ProductMaster;
import com.ros.inventory.model.supplier.Supplier;

public interface SupplierService {

	Supplier addSupplier(ExternalSupplierDto externalSupplierDto, UUID restaurantId)
			throws SupplierAlreadyExistsException, RestaurantNotFoundException;

	Supplier addSupplier(InternalSupplierDto internalSupplierDto, UUID restaurantId)
			throws SupplierAlreadyExistsException, RestaurantNotFoundException;

	Supplier editSupplier(SupplierDto supplierDto) throws SupplierNotFoundException;

	List<ProductMaster> updateProductMaster(UUID supplierId, List<ProductMasterDto> productMastersDtos)
			throws SupplierNotFoundException;

	public String getSupplierProfile(UUID id) throws SupplierNotFoundException;

	public BankDetails viewSupplierBankDetailss(UUID SupplierId) throws SupplierNotFoundException;

	public Supplier removeSupplier(UUID supplierId) throws SupplierNotFoundException;

	public List<ProductMasterDto> viewSupplierProductMaster(UUID supplierId) throws SupplierNotFoundException;

	public ProductMasterDto addProductMasterList(ProductMasterDto productMasterDto)
			throws SupplierNotFoundException, ProductNotFoundException;

	public boolean updateProductMaster(ProductMasterDto productMasterDto)
			throws SupplierNotFoundException, ProductNotFoundException;

}
