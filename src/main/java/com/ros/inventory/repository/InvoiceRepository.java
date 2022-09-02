package com.ros.inventory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.invoice.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,UUID>{

	
}
