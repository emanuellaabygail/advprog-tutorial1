package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9def46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEdit_Success() {
        // Create and save initial product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Create updated product data
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        // Perform edit
        Product editedProduct = productRepository.edit(product.getProductId(), updatedProduct);

        // Verify the edit
        assertNotNull(editedProduct);
        assertEquals(product.getProductId(), editedProduct.getProductId());
        assertEquals("Sampo Cap Usep", editedProduct.getProductName());
        assertEquals(200, editedProduct.getProductQuantity());

        // Verify the edit persists in repository
        Product foundProduct = productRepository.findById(product.getProductId());
        assertEquals("Sampo Cap Usep", foundProduct.getProductName());
        assertEquals(200, foundProduct.getProductQuantity());
    }

    @Test
    void testEdit_ProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(200);

        Product editedProduct = productRepository.edit("non-existent-id", updatedProduct);
        assertNull(editedProduct);
    }

    @Test
    void testEdit_MultipleEditsOnSameProduct() {
        // Create and save initial product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // First edit
        Product firstUpdate = new Product();
        firstUpdate.setProductName("Sampo Cap Usep");
        firstUpdate.setProductQuantity(200);
        Product firstEdit = productRepository.edit(product.getProductId(), firstUpdate);

        // Second edit
        Product secondUpdate = new Product();
        secondUpdate.setProductName("Sampo Cap Budi");
        secondUpdate.setProductQuantity(300);
        Product secondEdit = productRepository.edit(product.getProductId(), secondUpdate);

        // Verify final state
        assertNotNull(secondEdit);
        assertEquals(product.getProductId(), secondEdit.getProductId());
        assertEquals("Sampo Cap Budi", secondEdit.getProductName());
        assertEquals(300, secondEdit.getProductQuantity());

        // Verify the final edit persists in repository
        Product foundProduct = productRepository.findById(product.getProductId());
        assertEquals("Sampo Cap Budi", foundProduct.getProductName());
        assertEquals(300, foundProduct.getProductQuantity());
    }

    @Test
    void testDelete_Success() {
        // Create and save a product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Verify product exists
        assertNotNull(productRepository.findById(product.getProductId()));

        // Delete the product
        productRepository.delete(product.getProductId());

        // Verify product is deleted
        assertNull(productRepository.findById(product.getProductId()));
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testDelete_ProductNotFound() {
        // Verify no exception is thrown when trying to delete non-existent product
        assertDoesNotThrow(() -> productRepository.delete("non-existent-id"));
    }

    @Test
    void testDelete_MultipleProductsAndVerifyOthersRemain() {
        // Create and save first product
        Product product1 = new Product();
        product1.setProductId("product-1");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        // Create and save second product
        Product product2 = new Product();
        product2.setProductId("product-2");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        // Delete first product
        productRepository.delete(product1.getProductId());

        // Verify first product is deleted
        assertNull(productRepository.findById(product1.getProductId()));

        // Verify second product still exists with correct data
        Product remainingProduct = productRepository.findById(product2.getProductId());
        assertNotNull(remainingProduct);
        assertEquals(product2.getProductName(), remainingProduct.getProductName());
        assertEquals(product2.getProductQuantity(), remainingProduct.getProductQuantity());

        // Verify only one product remains in total
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productIterator.next();
        assertFalse(productIterator.hasNext());
    }
}
