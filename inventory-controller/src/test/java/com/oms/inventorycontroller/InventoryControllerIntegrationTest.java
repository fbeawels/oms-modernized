package com.oms.inventorycontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.entity.Inventory;
import com.oms.inventorycontroller.dto.InventoryServiceFetchInventoryInDTO;
import com.oms.repository.InventoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.oms.config.InventoryConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InventoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        // Clear the repository before each test
        inventoryRepository.deleteAll();
        
        // Add test data
        Inventory inventory = new Inventory("123", "281", 10);
        inventoryRepository.save(inventory);
    }

    @Test
    public void testFetchInventory() throws Exception {
        // Arrange
        InventoryServiceFetchInventoryInDTO inDTO = new InventoryServiceFetchInventoryInDTO();
        inDTO.setSkuId("123");
        
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/inventoryService/fetchInventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.retVal").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.retVal.skuId").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.retVal.storeId").value("281"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.retVal.quantity").value(10));
    }

    @Test
    public void testFetchInventoryNotFound() throws Exception {
        // Arrange
        InventoryServiceFetchInventoryInDTO inDTO = new InventoryServiceFetchInventoryInDTO();
        inDTO.setSkuId("999");
        
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                .post("/inventoryService/fetchInventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.retVal").doesNotExist());
    }
}
