package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Iterator;

@Repository
public class ProductRepository implements GenericRepository<Product> {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        if (product.getProductId() == null) {
            product.setProductId(UUID.randomUUID().toString());
        }
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public Product findById(String id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product edit(String id, Product updatedProduct) {
        Product product = findById(id);
        if (product != null) {
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
        }
        return product;
    }

    @Override
    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}
