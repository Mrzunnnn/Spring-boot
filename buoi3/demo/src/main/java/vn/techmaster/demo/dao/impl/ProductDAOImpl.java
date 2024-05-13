package vn.techmaster.demo.dao.impl;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.dao.ProductDAO;
import vn.techmaster.demo.database.ProductDB;
import vn.techmaster.demo.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> findAll() {
        return ProductDB.products;
    }

    @Override
    public Product findId(String id) {
        for (Product product : ProductDB.products){
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null ;
    }

    @Override
    public List<Product> findName(String prefix) {
        List<Product> result = new ArrayList<>();
        for (Product product :ProductDB.products){
            if (product.getName().toLowerCase().startsWith(prefix.toLowerCase())){
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> findPrice(int min, int max) {
        List<Product> result = new ArrayList<>();
        for (Product product : ProductDB.products){
            if (product.getPrice()>=min && product.getPrice()<=max){
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> findBrand(String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product :ProductDB.products){
            if (product.getBrand().toLowerCase().equals(brand.toLowerCase())){
             result.add(product);
            }
        }
        return result;
    }

    @Override
    public Product findMax(String brand) {
        List<Product> result = new ArrayList<>();
        for (Product product :ProductDB.products){
            if (product.getBrand().toLowerCase().equals(brand.toLowerCase())){
                result.add(product);
            }
        }
        result.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getPrice()- o1.getPrice();
            }
        });
        Product maxPrice = result.get(0);
        return maxPrice;
    }
}
