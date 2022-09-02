package com.ros.inventory.model.purchaseorder;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Attachments implements Serializable {

	private static final long serialVersionUID = -2616474608009736714L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attachments_id")
	private UUID attachementId;

	@Column(name = "attachments_discrepency")
	private String discrepency;

	@Column(name = "attachments_creditNote")
	private String creditNote;

	@Column(name = "attachments_invoiceReceived")
	private String invoiceReceived;

}
