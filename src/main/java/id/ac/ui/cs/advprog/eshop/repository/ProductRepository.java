package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
         if (product.getProductId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String id){
        for (Product product : productData){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        throw new IllegalArgumentException("Product doesn't exist");
    }

    public Product edit(Product editedProduct) {
        String id = editedProduct.getProductId();
        Product existingProduct = findById(id);
        int indexOfProduct = productData.indexOf(existingProduct);
        productData.set(indexOfProduct, editedProduct);
        return editedProduct;
    }

    public Product delete(String productId) {
        Product product = findById(productId);
        productData.remove(product);
        return product;
    }
}