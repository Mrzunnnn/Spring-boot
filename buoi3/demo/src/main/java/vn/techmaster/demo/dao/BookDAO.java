package vn.techmaster.demo.dao;

import vn.techmaster.demo.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findALl();

    Book findId(int id);

    List<Book> sortBookByYear();

    List<Book> searchByName(String keyword);

    List<Book> getBooksInRangeYear(int startYear, int endYear);
}
