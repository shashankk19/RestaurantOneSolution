package com.ros.inventory.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.purchaseorder.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID> {

	@Query(value="select p.poNumber,p.purchaseOrderDate,s.supplierName,s.type,p.value from PurchaseOrder p "
			+ "inner join p.supplier s where p.purchaseOrderType = :purchaseOrderType")
	public List<PurchaseOrder> getDrafts(@Param(value="purchaseOrderType") String purchaseOrderType);
}
