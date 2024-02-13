package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productServiceImpl;
    @BeforeEach
    void setUp(){
    }

    @Test
    void testCreate(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productServiceImpl.create(product);
        assertEquals(product.getProductId(), createdProduct.getProductId());
    }

    @Test
    void testFindAll() {
        List<Product> productData = new ArrayList<>();

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productData.add(product);

        when(productRepository.findAll()).thenReturn(productData.iterator());
        Iterator<Product> result = productServiceImpl.findAll().iterator();

        assertTrue(result.hasNext());
        Product savedProduct = result.next();
        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productServiceImpl.create(product);
        
        when(productRepository.findById(product.getProductId())).thenReturn(product);

        Product foundProduct = productServiceImpl.findById(product.getProductId());

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testEdit(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        
        when(productRepository.edit(product)).thenReturn(product);
        Product editedProduct = productServiceImpl.editProduct(product);

        assertEquals(product, editedProduct);
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testDelete(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.delete(product.getProductId())).thenReturn(product);
        Product deletedProduct = productServiceImpl.deleteProduct(product.getProductId());

        assertEquals(product, deletedProduct);
        verify(productRepository, times(1)).delete(product.getProductId());
    }

}
