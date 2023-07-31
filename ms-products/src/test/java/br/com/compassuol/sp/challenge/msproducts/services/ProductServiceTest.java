package br.com.compassuol.sp.challenge.msproducts.services;

import br.com.compassuol.sp.challenge.msproducts.models.ProductEntity;
import br.com.compassuol.sp.challenge.msproducts.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductEntity> products = new ArrayList<>();
        products.add(new ProductEntity("Product 1", "Description 1", BigDecimal.valueOf(10.99)));
        products.add(new ProductEntity("Product 2", "Description 2", BigDecimal.valueOf(20.99)));

        when(productRepository.findAll()).thenReturn(products);

        List<ProductEntity> result = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();
        assert result.size() == 2;
        assert result.get(0).getName().equals("Product 1");
        assert result.get(1).getName().equals("Product 2");
    }

    @Test
    public void testGetProductById() {
        Long productId = 1L;
        ProductEntity product = new ProductEntity("Product 1", "Description 1", BigDecimal.valueOf(10.99));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductEntity result = productService.getProductById(productId);

        verify(productRepository, times(1)).findById(productId);
        assert result.getName().equals("Product 1");
    }

    @Test
    public void testSaveProduct() {
        ProductEntity product = new ProductEntity("Product 1", "Description 1", BigDecimal.valueOf(10.99));

        when(productRepository.save(product)).thenReturn(product);

        ProductEntity result = productService.saveProduct(product);

        verify(productRepository, times(1)).save(product);
        assert result.getName().equals("Product 1");
    }

    @Test
    public void testUpdateProduct() {
        Long productId = 1L;
        String status = "delievered";
        ProductEntity product = new ProductEntity("Product 1", "Description 1", BigDecimal.valueOf(10.99));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        ProductEntity result = productService.updateProduct(productId, status);

        verify(productRepository.findById(productId));
        Optional<ProductEntity> temp = productRepository.findById(productId);
        verify(temp.get().getStatus().equals("delievered"));
        assert result.getName().equals("Product 1");
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;
        ProductEntity product = new ProductEntity("Product 1", "Description 1", BigDecimal.valueOf(10.99));

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(product);
    }
}