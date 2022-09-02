package com.ros.inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.mapper.PurchaseOrderMapperImpl;
import com.ros.inventory.mapper.PurchasedProductMapper;
import com.ros.inventory.mapper.PurchasedProductMapperImpl;
import com.ros.inventory.mapper.StockMapper;
import com.ros.inventory.mapper.StockMapperImpl;
import com.ros.inventory.mapper.SiteTransferMapper;
import com.ros.inventory.mapper.SiteTransferMapperImpl;
import com.ros.inventory.mapper.InvoiceMapper;
import com.ros.inventory.mapper.InvoiceMapperImpl;
import com.ros.inventory.mapper.ProductMasterMapper;
import com.ros.inventory.mapper.ProductMasterMapperImpl;
import com.ros.inventory.mapper.SupplierMapper;
import com.ros.inventory.mapper.SupplierMapperImpl;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Value(value = "${swagger.url}")

    public String url;

    

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        List<Server> servers = new ArrayList<>();
        server.setUrl(url);
        servers.add(server);
        OpenAPI openAPI = new OpenAPI();
        openAPI.setServers(servers);
        return openAPI;

    }

	@Bean
	public SupplierMapper getSupplierMapper() {
		return new SupplierMapperImpl();
	}

	@Bean
	public ProductMasterMapper getProductMasterMapper() {
		return new ProductMasterMapperImpl();
	}

	@Bean
	public StockMapper getStockMapper() {
		return new StockMapperImpl();
	}

	@Bean
	public SiteTransferMapper getSiteTransfersMapper() {
		return new SiteTransferMapperImpl();
	}

	@Bean
	public PurchaseOrderMapper getPurchaseOrderMapper() {
		return new PurchaseOrderMapperImpl();
	}

	@Bean
	public PurchasedProductMapper getPurchasedProductMapper() {
		return new PurchasedProductMapperImpl();
	}
	
	@Bean
	public InvoiceMapper getInvoiceMapper() {
		return new InvoiceMapperImpl();
	}

}
