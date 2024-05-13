package vn.techmaster.demo.dao;

import vn.techmaster.demo.model.Product;
import vn.techmaster.demo.service.ProductService;

import javax.swing.plaf.LabelUI;
import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findId(String id);
    List<Product> findName(String prefix);
    List<Product> findPrice(int min,int max);
    List<Product> findBrand(String brand);
    Product findMax(String brand);
}
