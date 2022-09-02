package com.ros.inventory.controller.dto.supplier;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BankDetailsDto {

	private UUID id;

	@NotBlank
	private String bankName;

	@NotBlank
	private String branchAccount;

	@NotBlank
	private String accountHolderName;

	@Size(max = 6)
	private long bankCode;

	@Size(min = 7, max = 8)
	private long accountNumber;
}
