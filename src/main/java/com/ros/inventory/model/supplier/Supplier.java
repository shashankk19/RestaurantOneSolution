package com.ros.inventory.model.supplier;

import java.util.List;


import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Enumerated(EnumType.STRING)
	private SupplierType type;
	
	private String supplierName;
	
	private String profilePic;

	private String email;
	
	private long phoneNumber;
	
	private String restaurantName;

	@OneToOne(cascade = CascadeType.ALL)
	private Address generalAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private BankDetails bankDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	private BasicInformation basicInformation;	
	
	@OneToOne(cascade = CascadeType.ALL)
	private PrimaryContact primaryContact;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id")
	@JsonBackReference
	private List<ProductMaster> products;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public SupplierType getType() {
		return type;
	}

	public void setType(SupplierType type) {
		this.type = type;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Address getGeneralAddress() {
		return generalAddress;
	}

	public void setGeneralAddress(Address generalAddress) {
		this.generalAddress = generalAddress;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public BasicInformation getBasicInformation() {
		return basicInformation;
	}

	public void setBasicInformation(BasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

	public PrimaryContact getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(PrimaryContact primaryContact) {
		this.primaryContact = primaryContact;
	}

	public List<ProductMaster> getProducts() {
		return products;
	}

	public void setProducts(List<ProductMaster> products) {
		this.products = products;
	}
	
	

}
