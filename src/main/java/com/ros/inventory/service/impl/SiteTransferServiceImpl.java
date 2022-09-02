package com.ros.inventory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.controller.dto.SiteTransferDto;
import com.ros.inventory.exception.EmptyProductsListException;
import com.ros.inventory.exception.PurchaseOrderNotFoundException;
import com.ros.inventory.exception.SiteTransferNotFoundException;
import com.ros.inventory.mapper.SiteTransferMapper;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.purchaseorder.PurchasedProduct;
import com.ros.inventory.model.purchaseorder.SiteTransfer;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.repository.SiteTransferRepository;
import com.ros.inventory.service.SiteTransferService;

@Service
public class SiteTransferServiceImpl implements SiteTransferService {

	@Autowired
	public SiteTransferRepository siteTransferRepository;

	@Autowired
	public PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	public SiteTransferMapper siteTransfersMapper;

	@Override
	public List<SiteTransferDto> findAllSiteTransfers() throws SiteTransferNotFoundException {

		List<SiteTransfer> siteTransfers = siteTransferRepository.findAll();

		List<SiteTransferDto> siteTransfersDtos = new ArrayList<SiteTransferDto>();

		if (siteTransfers.isEmpty()) {

			throw new SiteTransferNotFoundException();
		} else {
			for (SiteTransfer siteTransfer : siteTransfers) {

				SiteTransferDto siteTransferDto = siteTransfersMapper.convertToSiteTransferDto(siteTransfer);

				siteTransfersDtos.add(siteTransferDto);
			}
		}
		if (siteTransfersDtos.isEmpty()) {

			throw new SiteTransferNotFoundException("No site transfers are available");
		}
		return siteTransfersDtos;
	}

	@Override
	public List<PurchasedProduct> detailsOfSiteTransfers(UUID id)
			throws EmptyProductsListException, PurchaseOrderNotFoundException {
		List<PurchasedProduct> productsList = new ArrayList<PurchasedProduct>();
		UUID key = siteTransferRepository.findById(id).get().getPurchaseOrderId();

		if (!(key == null)) {

			PurchaseOrder purchaseorder = purchaseOrderRepository.getOne(key);
			productsList = purchaseorder.getProducts();
			if (!(productsList.isEmpty())) {
				return productsList;
			} else {
				throw new EmptyProductsListException("No products in the Order");
			}
		} else {
			throw new PurchaseOrderNotFoundException("SiteTransfers  does not exists");
		}

	}

}
