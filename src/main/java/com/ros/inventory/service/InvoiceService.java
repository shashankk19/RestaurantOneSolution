package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.controller.dto.InvoiceDto;
import com.ros.inventory.exception.InvoiceNotFoundException;

public interface InvoiceService {

	List<InvoiceDto> getMonthlyInvoice(int year, int month);

	InvoiceDto viewInvoice(UUID id) throws InvoiceNotFoundException;

	List<InvoiceDto> getYearlyInvoice(int year) throws InvoiceNotFoundException;
}
