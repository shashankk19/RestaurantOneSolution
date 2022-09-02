package com.ros.inventory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ros.inventory.model.purchaseorder.PurchasedProduct;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct, UUID> {

}
