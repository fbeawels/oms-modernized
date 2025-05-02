package com.oms;

import com.oms.entity.Inventory;
import com.oms.repository.InventoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InventoryApplicationTests {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void contextLoads() {
        // Ce test vérifie simplement que le contexte Spring se charge correctement
    }

    @Test
    public void testInventoryRepository() {
        // Arrange
        Inventory inventory = new Inventory("test-sku", "test-store", 100);
        
        // Act
        Inventory savedInventory = inventoryRepository.save(inventory);
        
        // Assert
        assertNotNull("Saved inventory should not be null", savedInventory);
        assertEquals("SKU ID should match", "test-sku", savedInventory.getSkuId());
        assertEquals("Store ID should match", "test-store", savedInventory.getStoreId());
        assertEquals("Quantity should match", 100, savedInventory.getQuantity());
    }
}
