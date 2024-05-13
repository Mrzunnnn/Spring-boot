package vn.techmaster.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import vn.techmaster.demo.model.Product;
import vn.techmaster.demo.utils.file.IFileReader;
import vn.techmaster.demo.utils.file.IFileReaderProduct;
@Configuration
public class InitDBProduct implements CommandLineRunner {
    @Autowired
    private IFileReaderProduct fileReaderProduct;
    @Override
    public void run(String... args) throws Exception {
        ProductDB.products = fileReaderProduct.readFile("Product.json");
        System.out.println("so luong product = "+ProductDB.products.size());
    }
}
