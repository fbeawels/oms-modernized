package com.oms.repository;

import com.oms.entity.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.oms.config.InventoryConfig;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class InventoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void testSaveInventory() {
        // Arrange
        Inventory inventory = new Inventory("123", "281", 10);
        
        // Act
        Inventory savedInventory = inventoryRepository.save(inventory);
        
        // Assert
        assertNotNull("Saved inventory should not be null", savedInventory);
        assertEquals("SKU ID should match", "123", savedInventory.getSkuId());
        assertEquals("Store ID should match", "281", savedInventory.getStoreId());
        assertEquals("Quantity should match", 10, savedInventory.getQuantity());
    }
    
    @Test
    public void testFindById() {
        // Arrange
        Inventory inventory = new Inventory("123", "281", 10);
        entityManager.persist(inventory);
        entityManager.flush();
        
        // Act
        Optional<Inventory> foundInventory = inventoryRepository.findById("123");
        
        // Assert
        assertTrue("Inventory should be found", foundInventory.isPresent());
        assertEquals("SKU ID should match", "123", foundInventory.get().getSkuId());
        assertEquals("Store ID should match", "281", foundInventory.get().getStoreId());
        assertEquals("Quantity should match", 10, foundInventory.get().getQuantity());
    }
    
    @Test
    public void testFindByIdNotFound() {
        // Act
        Optional<Inventory> foundInventory = inventoryRepository.findById("999");
        
        // Assert
        assertFalse("Inventory should not be found", foundInventory.isPresent());
    }
    
    @Test
    public void testUpdateInventory() {
        // Arrange
        Inventory inventory = new Inventory("123", "281", 10);
        entityManager.persist(inventory);
        entityManager.flush();
        
        // Act
        Optional<Inventory> foundInventory = inventoryRepository.findById("123");
        foundInventory.get().setQuantity(20);
        inventoryRepository.save(foundInventory.get());
        
        // Verify
        Inventory updatedInventory = entityManager.find(Inventory.class, "123");
        
        // Assert
        assertNotNull("Updated inventory should not be null", updatedInventory);
        assertEquals("Quantity should be updated", 20, updatedInventory.getQuantity());
    }
    
    @Test
    public void testDeleteInventory() {
        // Arrange
        Inventory inventory = new Inventory("123", "281", 10);
        entityManager.persist(inventory);
        
        Inventory inventory2 = new Inventory("456", "282", 20);
        entityManager.persist(inventory2);
        entityManager.flush();
        
        // Act
        inventoryRepository.deleteById("123");
        
        // Verify
        Optional<Inventory> deletedInventory = inventoryRepository.findById("123");
        Optional<Inventory> remainingInventory = inventoryRepository.findById("456");
        
        // Assert
        assertFalse("Deleted inventory should not be found", deletedInventory.isPresent());
        assertTrue("Remaining inventory should still be found", remainingInventory.isPresent());
    }
}
