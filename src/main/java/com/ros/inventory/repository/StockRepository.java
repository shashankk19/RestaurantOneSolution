package com.ros.inventory.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.stock.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock,UUID>{
	
	
	
}
