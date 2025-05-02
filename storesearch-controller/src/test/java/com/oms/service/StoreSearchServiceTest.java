package com.oms.service;

import com.oms.util.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StoreSearchServiceTest {

    @InjectMocks
    private StoreSearchService storeSearchService;

    @Mock
    private Logger logger;

    @Before
    public void setUp() {
        // Aucune configuration nécessaire pour le test simple
    }

    @Test
    public void testStoreSearch() {
        // Appel de la méthode à tester avec le code postal 75001
        List<String> response = storeSearchService.fetchStoresByZipCode("75001");

        // Vérifications
        Assert.assertNotNull(response);
        Assert.assertEquals(3, response.size()); // 2 magasins + le compteur
        Assert.assertTrue(response.contains("281"));
        Assert.assertTrue(response.contains("282"));
    }
}
