package vn.techmaster.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.ProductDAO;
import vn.techmaster.demo.model.Product;
import vn.techmaster.demo.service.ProductService;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productDAO.findId(id);
    }

    @Override
    public List<Product> getProductByName(String prefix) {
        return productDAO.findName(prefix);
    }

    @Override
    public List<Product> getProductByPrice(int min, int max) {
        return productDAO.findPrice(min,max);
    }

    @Override
    public List<Product> getProductBybrand(String brand) {
        return productDAO.findBrand(brand);
    }

    @Override
    public Product getProductMaxPrice(String brand) {
        return productDAO.findMax(brand);
    }
}
