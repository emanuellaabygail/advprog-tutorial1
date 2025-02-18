package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("1");
        sampleProduct.setProductName("Sample Product");
        sampleProduct.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("createProduct", viewName);
    }

    @Test
    void testCreateProduct() {
        String viewName = productController.createProduct(sampleProduct, model);
        verify(productService).create(sampleProduct);
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(sampleProduct);
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        verify(model).addAttribute("products", productList);
        assertEquals("productList", viewName);
    }

    @Test
    void testEditProductPage() {
        when(productService.findById("1")).thenReturn(sampleProduct);

        String viewName = productController.editProductPage("1", model);

        verify(model).addAttribute("product", sampleProduct);
        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProductPost() {
        String viewName = productController.editProductPost("1", sampleProduct, model);

        verify(productService).edit("1", sampleProduct);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProductPage() {
        String viewName = productController.deleteProductPage("1", model);

        verify(productService).delete("1");
        assertEquals("redirect:/product/list", viewName);
    }
}
