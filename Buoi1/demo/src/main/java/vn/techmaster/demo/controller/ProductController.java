package vn.techmaster.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.model.Product;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ProductController {
    private final List<Product> products;
    public ProductController() {
        products = new ArrayList<>();
        products.add(new Product("1", "Macbook M1", "Macbook mới nhất", 400,"Apple"));
        products.add(new Product("2", "Macbook air", "Nhỏ gọn",200,"Apple"));
        products.add(new Product("3", "Airpod pro ", "Âm thanh tốt ", 100,"Apple"));
        products.add(new Product("4", "Iphone 15promax", "Iphone mới nhất", 200,"Apple"));
        products.add(new Product("5", "Apple watch ultra", "Đồng hồ thông minh mới nhất ", 150,"Apple"));
    }
    @GetMapping("/products")  //http://localhost:8080/books

    public List<Product> getStudents(){
        return products;
    }
}
