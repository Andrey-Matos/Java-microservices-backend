package br.com.compassuol.sp.challenge.msproducts.service;

import br.com.compassuol.sp.challenge.msproducts.model.ProductEntity;
import br.com.compassuol.sp.challenge.msproducts.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;


@Service
public class ProductService {
    public  ProductService(){}
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());

                    return productRepository.save(existingProduct);
                })
                .orElse(null);
    }

    public List<ProductEntity> getProducts(int page, int linesPerPage, String direction, String orderBy) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        Pageable pageable = PageRequest.of(page - 1, linesPerPage, sort);
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }
}
