package vn.techmaster.demo.utils.file;

import vn.techmaster.demo.dao.BookDAO;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.model.Product;

import java.util.List;

public interface IFileReader {
    List<Book> readFile(String filePath);
}
