package com.ros.inventory.controller.dto.supplier;

import java.util.Date;

import com.ros.inventory.model.supplier.ProductMasterID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMasterDto {

	private ProductMasterID id;

	private ProductDto product;

	private String unitMeasurement;

	private Double pricePerUnit;

	private Double vat;

	private Date effectiveDate;
}
