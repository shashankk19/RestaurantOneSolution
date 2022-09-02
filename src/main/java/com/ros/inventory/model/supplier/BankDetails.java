package com.ros.inventory.model.supplier;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@NotBlank
	private String bankName;
	
	@NotBlank
	private String branchAccount;
	
	@NotBlank
	private String accountHolderName;
	
	
	private long bankCode;
	
	
	private long accountNumber;

	@Override
	public String toString() {
		return "BankDetails [id=" + id + ", bankName=" + bankName + ", branchAccount=" + branchAccount
				+ ", accountHolderName=" + accountHolderName + ", bankCode=" + bankCode + ", accountNumber="
				+ accountNumber + "]";
	}
	
	

}
