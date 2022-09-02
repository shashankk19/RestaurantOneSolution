package com.ros.inventory.repository;

import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.purchaseorder.Product;
import com.ros.inventory.model.supplier.BankDetails;
import com.ros.inventory.model.supplier.ProductMaster;
import com.ros.inventory.model.supplier.Restaurant;
import com.ros.inventory.model.supplier.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
	
	@Query(value = "Select s from Supplier s where s.supplierName=:name")
	public List<Supplier> getSupplierList(@Param(value = "name") String supplierName);
	
}
