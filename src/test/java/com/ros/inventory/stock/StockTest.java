package com.ros.inventory.stock;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.util.UUID;



import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.ros.inventory.controller.StockController;
import com.ros.inventory.model.stock.Stock;
import com.ros.inventory.model.stock.StockType;
import com.ros.inventory.service.StockService;

@WebMvcTest(value=StockController.class)
public class StockTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockService stockService;

	private static ObjectMapper mapper=new ObjectMapper();


	@Test
	public void testApproveStock() throws Exception
	{

	Stock stock=new Stock();
	stock.setId(UUID.fromString("0cb58e9e-7db5-4899-8469-42c9b3b04aad"));
	stock.setStockType(StockType.APPROVED);
	String jsonString=mapper.writeValueAsString(stock);
	when(stockService.approveStock(ArgumentMatchers.any())).thenReturn(stock);
	mockMvc.perform(put("/stocks/ApproveStock/0cb58e9e-7db5-4899-8469-42c9b3b04aad"))
	.andExpect(status().isOk());


	}







	}	


