package com.oms.inventorycontroller;

import com.oms.entity.Inventory;
import com.oms.inventorycontroller.dto.InventoryServiceFetchInventoryInDTO;
import com.oms.inventorycontroller.dto.InventoryServiceFetchInventoryOutDTO;
import com.oms.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryServiceController inventoryServiceController;

    @Test
    public void testFetchInventory() {
        // Arrange
        String skuId = "123";
        Inventory inventory = new Inventory(skuId, "281", 10);
        
        InventoryServiceFetchInventoryInDTO inDTO = new InventoryServiceFetchInventoryInDTO();
        inDTO.setSkuId(skuId);
        
        when(inventoryService.fetchInventory(skuId)).thenReturn(inventory);

        // Act
        ResponseEntity<InventoryServiceFetchInventoryOutDTO> response = 
            inventoryServiceController.fetchInventory(inDTO);

        // Assert
        assertNotNull("Response should not be null", response);
        assertEquals("HTTP status should be OK", HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body should not be null", response.getBody());
        assertNotNull("Return value should not be null", response.getBody().getRetVal());
        assertEquals("SKU ID should match", skuId, response.getBody().getRetVal().getSkuId());
        assertEquals("Quantity should match", 10, response.getBody().getRetVal().getQuantity());
    }

    @Test
    public void testFetchInventoryNotFound() {
        // Arrange
        String skuId = "999";
        
        InventoryServiceFetchInventoryInDTO inDTO = new InventoryServiceFetchInventoryInDTO();
        inDTO.setSkuId(skuId);
        
        when(inventoryService.fetchInventory(skuId)).thenReturn(null);

        // Act
        ResponseEntity<InventoryServiceFetchInventoryOutDTO> response = 
            inventoryServiceController.fetchInventory(inDTO);

        // Assert
        assertNotNull("Response should not be null", response);
        assertEquals("HTTP status should be OK", HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body should not be null", response.getBody());
        assertEquals("Return value should be null", null, response.getBody().getRetVal());
    }
}
