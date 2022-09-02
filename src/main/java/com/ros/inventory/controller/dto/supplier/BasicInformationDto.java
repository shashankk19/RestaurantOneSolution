package com.ros.inventory.controller.dto.supplier;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BasicInformationDto {

	private UUID id;

	@NotBlank
	private String businessName;

	@NotBlank
	private String legalTradeName;

	@NotBlank
	private long mobileNumber;

	private long telephone;

	@NotBlank
	private String email;

	private String fax;
}
