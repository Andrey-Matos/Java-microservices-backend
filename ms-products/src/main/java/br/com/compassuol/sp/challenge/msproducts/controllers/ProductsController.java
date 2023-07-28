package br.com.compassuol.sp.challenge.msproducts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.compassuol.sp.challenge.msproducts.models.ProductEntity;
import br.com.compassuol.sp.challenge.msproducts.services.ProductService;

@RestController
public class ProductsController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<ProductEntity> getAllProducts(){
		return service.getAllProducts();
	}
	
	@GetMapping("/products/{id}")
	public ProductEntity getProductById(@PathVariable Long id){
		return service.getProductById(id);
	}
	
	@PostMapping("/products")
    public String saveProduct(@RequestBody ProductEntity product){
        return service.saveProduct(product);
    }
    @PutMapping("/products/{id}")
    public String updateProduct(@RequestBody ProductEntity product, @PathVariable Long id) {
        return service.updateProduct(product, id);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return service.deleteProduct(id);
    }
}
