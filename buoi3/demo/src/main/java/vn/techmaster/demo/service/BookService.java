package vn.techmaster.demo.service;

import vn.techmaster.demo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();
    Book getBookById(int id);
    List<Book> sortBookByYear();
    List<Book> searchByName(String keyword);
    List<Book> getBooksInRangeYear(int startYear,int endYear);
}
