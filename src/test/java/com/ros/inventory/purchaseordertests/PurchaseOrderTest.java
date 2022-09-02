package com.ros.inventory.purchaseordertests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ros.inventory.controller.PurchaseOrderController;
import com.ros.inventory.controller.dto.PurchaseOrderDto;
import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.service.PurchaseOrderService;

@WebMvcTest(value = PurchaseOrderController.class)
public class PurchaseOrderTest {
	
	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private PurchaseOrderService purchaseOrderService;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testReSubmittedRejectedOrder() throws Exception
	{
		PurchaseOrderDto po= new PurchaseOrderDto();
		po.setPoId(UUID.fromString("743d78ee-c74d-42bf-854a-f110c5da94e8"));
		po.setStatus(PurchaseOrderType.SUBMITTED);
		String JSON=mapper.writeValueAsString(po);
		System.out.println(po);
		when(purchaseOrderService.reSubmitRejectedOrder(ArgumentMatchers.any())).thenReturn(po);
		mockmvc.perform(put("/purchaseOrder/rejectToSubmit/743d78ee-c74d-42bf-854a-f110c5da94e8"))
		.andExpect(status().isOk());
	}
	
}
