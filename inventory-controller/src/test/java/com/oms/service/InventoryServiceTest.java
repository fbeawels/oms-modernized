package com.oms.service;

import com.oms.entity.Inventory;
import com.oms.repository.InventoryRepository;
import com.oms.util.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;
    
    @Mock
    private Logger logger;
    
    @InjectMocks
    private InventoryService inventoryService;

    @Before
    public void setUp() {
        // No need to manually set dependencies with @InjectMocks
    }

    @Test
    public void testFetchInventory() {
        // Arrange
        String skuId = "123";
        Inventory inventory = new Inventory(skuId, "281", 10);
        when(inventoryRepository.findById(skuId)).thenReturn(Optional.of(inventory));

        // Act
        Inventory response = inventoryService.fetchInventory(skuId);

        // Assert
        Assert.assertNotNull("Response should not be null", response);
        Assert.assertEquals("Quantity should match", 10, response.getQuantity());
        verify(logger, times(1)).log(inventoryService.getClass().getName());
    }
    
    @Test
    public void testFetchInventoryNotFound() {
        // Arrange
        String skuId = "999";
        when(inventoryRepository.findById(skuId)).thenReturn(Optional.empty());

        // Act
        Inventory response = inventoryService.fetchInventory(skuId);

        // Assert
        Assert.assertNull("Response should be null for non-existent SKU", response);
        verify(logger, times(1)).log(inventoryService.getClass().getName());
    }
    
    @Test
    public void testCreateInventory() {
        // Arrange
        Inventory inventory = new Inventory("123", "281", 10);
        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        // Act
        Inventory response = inventoryService.createInventory(inventory);

        // Assert
        Assert.assertNotNull("Response should not be null", response);
        Assert.assertEquals("SKU ID should match", "123", response.getSkuId());
        Assert.assertEquals("Quantity should match", 10, response.getQuantity());
        verify(logger, times(1)).log(inventoryService.getClass().getName());
        verify(inventoryRepository, times(1)).save(inventory);
    }
}
