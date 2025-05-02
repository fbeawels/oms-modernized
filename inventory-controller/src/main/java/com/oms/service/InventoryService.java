package com.oms.service;

import com.oms.entity.Inventory;
import com.oms.repository.InventoryRepository;
import com.oms.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    Logger logger;

    public Inventory fetchInventory(String skuId) {
        logger.log(this.getClass().getName());
        Optional<Inventory> inventory = inventoryRepository.findById(skuId);
        return inventory.orElse(null);
    }

    public Inventory createInventory(Inventory inventory) {
        logger.log(this.getClass().getName());
        return inventoryRepository.save(inventory);
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
