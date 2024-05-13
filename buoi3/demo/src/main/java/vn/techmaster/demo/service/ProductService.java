package vn.techmaster.demo.service;

import vn.techmaster.demo.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String id);
    List<Product> getProductByName(String prefix);
    List<Product> getProductByPrice(int min,int max);
    List<Product> getProductBybrand(String brand);
    Product getProductMaxPrice(String brand);
}
