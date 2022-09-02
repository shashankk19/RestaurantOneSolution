package com.ros.inventory.controller.dto.supplier;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddressDto {

	private UUID id;

	@NotBlank
	private String address;

	@NotBlank
	private long zipcode;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	private String country;
}
