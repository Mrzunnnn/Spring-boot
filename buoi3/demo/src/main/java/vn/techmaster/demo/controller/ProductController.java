package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.model.Product;
import vn.techmaster.demo.service.ProductService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.CREATED);
    }




    @GetMapping("/{id}")

    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product!=null){
            return ResponseEntity.ok(product);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @GetMapping("/name-starts/{prefix}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String prefix){
        List<Product> products = productService.getProductByName(prefix);
        if (products!=null){
            return ResponseEntity.ok(products);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPrice(@PathVariable int min,@PathVariable int max){
        List<Product> products = productService.getProductByPrice(min,max);
        if (products!=null){
            return ResponseEntity.ok(products);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductBybrand(@PathVariable String brand){
        List<Product> products = productService.getProductBybrand(brand);
        if (products!=null){
            return ResponseEntity.ok(products);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/brand/{brand}/max-price")
    public ResponseEntity<Product> getProductMaxPrice(@PathVariable String brand){
        Product product = productService.getProductMaxPrice(brand);
        if (product!=null){
            return ResponseEntity.ok(product);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

