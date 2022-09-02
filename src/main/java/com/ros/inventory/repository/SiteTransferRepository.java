package com.ros.inventory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ros.inventory.model.purchaseorder.SiteTransfer;

@Repository
public interface SiteTransferRepository extends JpaRepository<SiteTransfer, UUID> {
	

}
