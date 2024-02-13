package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @Mock
    private Model model;
    @InjectMocks
    private ProductController productController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String expectedName = "createProduct";
        assertEquals(expectedName, productController.createProductPage(model));
    }

    @Test
    void testEditProductPage(){
        Product product = new Product();

        when(productService.findById(product.getProductId())).thenReturn(product);
        String expectedName = "editProduct";
        assertEquals(expectedName, productController.editProductPage(product.getProductId(), model));
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        String expectedName = "productList";
        assertEquals(expectedName, productController.productListPage(model));
        verify(model, times(1)).addAttribute("products", productList);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String expectedName = "redirect:list";
        assertEquals(expectedName, productController.createProductPost(product, model));
        verify(productService, times(1)).create(product);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String expectedName = "redirect:list";
        assertEquals(expectedName, productController.editProductPost(product, model));
        verify(productService, times(1)).editProduct(product);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        String expectedName = "redirect:../list";
        assertEquals(expectedName, productController.deleteProduct(product.getProductId(), model));
        verify(productService, times(1)).deleteProduct(product.getProductId());
    }
}
