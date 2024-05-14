package vn.techmaster.demo.dao.impl;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.dao.ProductDAO;
import vn.techmaster.demo.database.ProductDB;
import vn.techmaster.demo.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> findAll() {
        return ProductDB.products;
    }

    @Override
    public Product findId(String id) {

        return findAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findName(String prefix) {

        return findAll().stream()
                .filter(product -> product.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findPrice(int min, int max) {

        return findAll().stream()
                .filter(product -> product.getPrice()>=min && product.getPrice() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findBrand(String brand) {

        return findAll().stream()
                .filter(product -> product.getBrand().toLowerCase().equals(brand.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Product findMax(String brand) {
        return findBrand(brand).stream()
                .max((p1,p2)-> p1.getPrice()- p2.getPrice())
                .orElse(null);
    }
}
