package org.xproce.revormclass.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xproce.revormclass.dao.entities.Product;
import org.xproce.revormclass.dao.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService implements ProductManager {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        if (product.getPrice() > 0) {
            return productRepository.save(product);
        } else {
            System.out.println("the price is not valid");
            return null;
        }
    }
    @Override
    public Product updateProduct(Product product) {
        if (product.getPrice() > 0) {
            return productRepository.save(product);
        } else {
            System.out.println("the price is not valid");
            return null;
        }
    }
    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Page<Product> getAllProducts(int page, int taille) {
        return productRepository.findAll(PageRequest.of(page,taille));
    }
    @Override
    public Product getProductById(Integer id) {
       return  productRepository.findById(id).get();
    }

    /*@Override
    public List<Product> searchProducts(String keyword) {
        return null;
    }*/

    @Override
    public Page<Product> searchProducts(String keyword,int page, int taille) {
        if(keyword==null){
            return productRepository.findAll(PageRequest.of(page,taille));
        }
        else{
            return productRepository.findByDescriptionContains(keyword, PageRequest.of(page, taille));
        }
    }
}