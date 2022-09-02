package com.ros.inventory.purchaseordertests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ros.inventory.controller.PurchaseOrderController;
import com.ros.inventory.controller.dto.PurchaseOrderDto;
import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.service.PurchaseOrderService;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseOrderControllerTest {
	
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();
	
	@Mock
	private PurchaseOrderRepository purchaseOrderRepository;
	
	private PurchaseOrderService   purchaseOrderService;
	
	@InjectMocks
	private PurchaseOrderController purchaseOrderController;
	
	@BeforeAll
	public void setUp() {
		MockitoAnnotations.initMocks(getClass());
		this.mockMvc=MockMvcBuilders.standaloneSetup(purchaseOrderController).build();
	}
	
	
	@Test
	public void reSubmitRejectedOrderTest() throws Exception{
		PurchaseOrderDto purchaseOrder = new PurchaseOrderDto();
		purchaseOrder.setPoId(UUID.fromString("3d48e417-ebeb-499c-ac13-2b84ec6df6c0"));
		purchaseOrder.setStatus(PurchaseOrderType.SUBMITTED);
		
		Mockito.when(purchaseOrderService.reSubmitRejectedOrder(ArgumentMatchers.any())).thenReturn(purchaseOrder);
		
		mockMvc.perform(MockMvcRequestBuilders
				.put("/purchaseOrder/rejectToSubmit/3d48e417-ebeb-499c-ac13-2b84ec6df6c0")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
