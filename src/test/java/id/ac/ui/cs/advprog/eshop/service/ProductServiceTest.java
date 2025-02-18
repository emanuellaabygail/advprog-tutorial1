package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
    }

    @Test
    void testCreate() {
        when(productRepository.create(product1)).thenReturn(product1);
        Product result = productService.create(product1);
        assertEquals(product1, result);
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAll() {
        Iterator<Product> iterator = Arrays.asList(product1, product2).iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> products = productService.findAll();
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }

    @Test
    void testFindById() {
        when(productRepository.findById("1")).thenReturn(product1);
        Product result = productService.findById("1");
        assertEquals(product1, result);
    }

    @Test
    void testEdit() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Updated Product");

        when(productRepository.edit("1", updatedProduct)).thenReturn(updatedProduct);
        Product result = productService.edit("1", updatedProduct);
        assertEquals("Updated Product", result.getProductName());
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete("1");
        productService.delete("1");
        verify(productRepository, times(1)).delete("1");
    }
}
