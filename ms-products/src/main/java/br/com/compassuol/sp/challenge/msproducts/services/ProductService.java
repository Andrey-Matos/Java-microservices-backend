package br.com.compassuol.sp.challenge.msproducts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compassuol.sp.challenge.msproducts.models.ProductEntity;
import br.com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // GET ALL
    public List<ProductEntity> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products;
    }

    // GET BY ID
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // POST
    public ProductEntity saveProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    // PUT
    public ProductEntity updateProduct(Long id, String status) {
        ProductEntity temp = getProductById(id);
        temp.setStatus(status);
        return productRepository.save(temp);

    }

    // DELETE
    public void deleteProduct(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        productRepository.delete(product.get());
    }
}
