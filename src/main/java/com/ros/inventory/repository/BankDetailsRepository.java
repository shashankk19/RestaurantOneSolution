package com.ros.inventory.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ros.inventory.model.supplier.BankDetails;

public interface BankDetailsRepository extends JpaRepository<BankDetails, UUID> {
	
	@Query(value = "Select b from BankDetails b")
	public List<BankDetails> getBankDetails();

}
