package vn.techmaster.demo.utils.file;

import vn.techmaster.demo.model.Product;

import java.util.List;

public interface IFileReaderProduct {
    List<Product> readFile(String filePath);
}
