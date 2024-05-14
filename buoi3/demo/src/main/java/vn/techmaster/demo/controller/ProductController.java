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
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products",products);
        return "AllProducts";
    }




    @GetMapping("/{id}")

    public String getProductById(Model model,@PathVariable String id) {
        Product product = productService.getProductById(id);
        model.addAttribute("products",product);
        return "id";
    }




    @GetMapping("/name-starts/{prefix}")
    public String getProductByName(Model model,@PathVariable String prefix){
        List<Product> products = productService.getProductByName(prefix);
        model.addAttribute("products",products);
        return "SearchPrefix";
    }



    @GetMapping("/price/{min}/{max}")
    public String getProductByPrice(Model model,@PathVariable int min,@PathVariable int max){
        List<Product> products = productService.getProductByPrice(min,max);
        model.addAttribute("products",products);
        return "InRangePrice" ;
    }




    @GetMapping("/brand/{brand}")
    public String getProductBybrand(Model model,@PathVariable String brand){
        List<Product> products = productService.getProductBybrand(brand);
        model.addAttribute("products",products);
        return "SearchBrand" ;
    }



    @GetMapping("/brand/{brand}/max-price")
    public String getProductMaxPrice(Model model,@PathVariable String brand){
        Product product = productService.getProductMaxPrice(brand);
        model.addAttribute("products",product);
        return "MaxProductPrice";
    }
}

