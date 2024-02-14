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

class ProductControllerTest {
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
        String id = product.getProductId();

        when(productService.findById(id)).thenReturn(product);
        String expectedName = "editProduct";
        assertEquals(expectedName, productController.editProductPage(id, model));
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
        String id = product.getProductId();

        assertEquals(expectedName, productController.deleteProduct(id, model));
        verify(productService, times(1)).deleteProduct(id);
    }
}
