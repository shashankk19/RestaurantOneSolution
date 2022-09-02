package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ros.inventory.controller.dto.AddStockDto;
import com.ros.inventory.controller.dto.EditWastageDto;
import com.ros.inventory.controller.dto.WastageDto;
import com.ros.inventory.model.stock.Stock;
import com.ros.inventory.model.stock.Wastage;
import com.ros.inventory.controller.dto.ClosedStockDto;

@Mapper
public interface StockMapper {

	@Mapping(source="stock.id", target="stockId")
	@Mapping(source="stock.startDate", target="startDate")
	@Mapping(source="stock.endDate", target="endDate")
	public AddStockDto convertToAddStockDto(Stock stock);
	
	@Mapping(source="wastage.wastageId", target="wastageId")
	@Mapping(source="wastage.quantity", target="quantity")
	public EditWastageDto convertToEditWastageDto(Wastage wastage);
	
	@Mapping(source="wastage.wastageId", target="wastageId")
	@Mapping(source="wastage.product" , target="product")
	@Mapping(source="wastage.pricePerUnit", target="pricePerUnit")
	@Mapping(source="wastage.quantity", target="quantity")
	public WastageDto convertToWastageDto(Wastage wastage);
	
	@Mapping(source="stock.startDate",target="startDate")
	@Mapping(source="stock.endDate", target = "endDate")
	@Mapping(source="stock.openingValue",target="openingValue")
	@Mapping(source="stock.closingValue", target="closingValue")
	@Mapping(source="stock.netSales", target="netSales")
	public ClosedStockDto convertToClosedStockDto(Stock stock);
}

