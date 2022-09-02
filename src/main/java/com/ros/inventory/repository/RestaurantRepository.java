package com.ros.inventory.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ros.inventory.model.supplier.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
	
	@Query(value = "Select r from Restaurant r")
	public List<Restaurant> getRestaurants();

}
