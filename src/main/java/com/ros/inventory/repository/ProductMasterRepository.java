package com.ros.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ros.inventory.model.supplier.ProductMaster;
import com.ros.inventory.model.supplier.ProductMasterID;

public interface ProductMasterRepository extends JpaRepository<ProductMaster, ProductMasterID> {
	
	

}
