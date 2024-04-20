package org.xproce.revormclass.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.xproce.revormclass.dao.entities.Product;
import java.util.List;
import java.util.Optional;

public interface ProductManager {
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Product product);
   public List<Product> getAllProducts();
    public Page<Product> getAllProducts(int page, int taille);
    public Product getProductById(Integer productId);
    //List<Product> searchProducts(String keyword);
    public Page<Product> searchProducts(String keyword, int page, int taille);





}
