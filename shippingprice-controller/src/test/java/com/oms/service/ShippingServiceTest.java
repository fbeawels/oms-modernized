package com.oms.service;

import com.oms.entity.Shipping;
import com.oms.repository.ShippingRepository;
import com.oms.util.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShippingServiceTest {

    @Mock
    private ShippingRepository shippingRepository;

    @InjectMocks
    private ShippingService shippingService;

    @BeforeEach
    public void setUp() {
        shippingService.setLogger(new Logger());
    }

    @Test
    public void testFetchShippingCharges() {
        // Arrange
        String skuId = "123";
        Shipping shipping = new Shipping(skuId, 3.00, 5.00, 7.00);
        
        when(shippingRepository.findById(skuId)).thenReturn(Optional.of(shipping));

        // Act
        Shipping response = shippingService.fetchShippingCharges(skuId);

        // Assert
        assertNotNull(response);
    }
    
    @Test
    public void testCreateShipping() {
        // Arrange
        Shipping shipping = new Shipping("456", 4.00, 6.00, 8.00);
        
        when(shippingRepository.save(shipping)).thenReturn(shipping);

        // Act
        Shipping response = shippingService.createShipping(shipping);

        // Assert
        assertNotNull(response);
    }
}
