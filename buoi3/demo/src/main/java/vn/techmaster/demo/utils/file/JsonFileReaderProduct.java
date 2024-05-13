package vn.techmaster.demo.utils.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import vn.techmaster.demo.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Component
public class JsonFileReaderProduct implements IFileReaderProduct{
    @Override
    public List<Product> readFile(String filePath) {
        System.out.println("Read data from JSON file");
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Product> products = mapper.readValue(new File(filePath), new TypeReference<List<Product>>() {});
            return products;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();  // Trả về danh sách rỗng nếu có lỗi xảy ra
        }
    }
}
