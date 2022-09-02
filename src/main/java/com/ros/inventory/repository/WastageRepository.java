package com.ros.inventory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.stock.Wastage;

@Repository
public interface WastageRepository extends JpaRepository<Wastage,UUID>{

}
