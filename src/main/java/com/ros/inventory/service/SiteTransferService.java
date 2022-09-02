package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ros.inventory.controller.dto.SiteTransferDto;
import com.ros.inventory.exception.EmptyProductsListException;
import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.exception.SiteTransferNotFoundException;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;

@Service
public interface SiteTransferService {

	List<SiteTransferDto> findAllSiteTransfers() throws SiteTransferNotFoundException;

	List<PurchasedProduct> detailsOfSiteTransfers(UUID siteTransferId)
			throws EmptyProductsListException, PurchaseOrderNotFoundException;

}
