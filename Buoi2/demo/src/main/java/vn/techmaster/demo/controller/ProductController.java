package vn.techmaster.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products;

    public ProductController() {
        products = new ArrayList<>();
        products.add(new Product("1", "Macbook M1", "Macbook mới nhất", 400, "Apple"));
        products.add(new Product("2", "Macbook air", "Nhỏ gọn", 200, "Apple"));
        products.add(new Product("3", "Airpod pro ", "Âm thanh tốt ", 100, "Apple"));
        products.add(new Product("4", "Iphone 15promax", "Iphone mới nhất", 200, "Apple"));
        products.add(new Product("5", "Apple watch ultra", "Đồng hồ thông minh mới nhất ", 150, "Apple"));
        products.add(new Product("6", "SamSung ZPold", "Máy gập thời thượng ", 400, "SamSung"));
        products.add(new Product("7", "Marshall Stanmore3", "Loa thời thượng", 170, "Marshall"));
        products.add(new Product("8", "SamSung S24", "Camera nâng cấp mới nhất", 300, "SamSung"));
        products.add(new Product("9", "SamSung Note20", "Máy ngon bổ rẻ", 100, "SamSung"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }




    @GetMapping("/{id}")

    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return ResponseEntity.ok(product);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @GetMapping("/name-starts/{prefix}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String prefix){
        List<Product> result = new ArrayList<>();
        for (Product product : products){
            if (product.getName().toLowerCase().equals(prefix.toLowerCase())){
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }



    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPrice(@PathVariable int min,@PathVariable int max){
        List<Product> result = new ArrayList<>();
        for (Product product :products){
            if (product.getPrice()>=min && product.getPrice()<=max){
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }




    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductBybrand(@PathVariable String brand){
        List<Product> result = new ArrayList<>();
        for (Product product :products){
            if (product.getBrand().toLowerCase().contains(brand.toLowerCase())){
                result.add(product);
            }
        }
        return ResponseEntity.ok(result);
    }



    @GetMapping("/brand/{brand}/max-price")
    public ResponseEntity<Product> getProductMaxPrice(@PathVariable String brand){
        List<Product> result = new ArrayList<>();
        for (Product product : products){
            if (product.getBrand().toLowerCase().contains(brand.toLowerCase())){
                result.add(product);}
        }
            result.sort(new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o2.getPrice() - o1.getPrice();
                }
            });
            Product maxPrice = result.get(0);
            return ResponseEntity.ok(maxPrice);
    }
}

