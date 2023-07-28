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
	private ProductRepository repository;
	
	// GET ALL
	public List<ProductEntity> getAllProducts(){
        List<ProductEntity> products = repository.findAll();
        return products;
    }
	
	// GET BY ID
	public ProductEntity getProductById(Long id) {
        Optional<ProductEntity> product = repository.findById(id);
        return product.get();
    }
	
	// POST
    public String saveProduct(ProductEntity product) {
        ProductEntity newProduct = repository.save(product);
        return "Produto criado com sucesso!";
    }
    
    // PUT
    public String updateProduct(ProductEntity product, Long id) {
        product.setId(id);
        ProductEntity newProduct = repository.save(product);
        return "Produto atualizado com sucesso!";
    }
    
    // DELETE
    public String deleteProduct(Long id) {
        Optional<ProductEntity> product = repository.findById(id);
        repository.delete(product.get());
        return "Produto de id "+id+" removido com sucesso!";
    }
	
}
