package com.ros.inventory.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ros.inventory.model.purchaseorder.Product;


public interface ProductRepository extends JpaRepository<Product, UUID>{
	
	@Query(value = "Select p from Product p")
	public List<Product>getProductsEntity();

}
