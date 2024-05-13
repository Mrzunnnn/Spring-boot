package vn.techmaster.demo.dao.impl;

import org.springframework.stereotype.Repository;
import vn.techmaster.demo.dao.BookDAO;
import vn.techmaster.demo.database.BookDB;
import vn.techmaster.demo.model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Repository
public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findALl() {
        return BookDB.books;
    }

    @Override
    public Book findId(int id) {
        for (Book book :BookDB.books){
            if (book.getId()==id){
                return book;
            }
        }return null;
    }

    @Override
    public List<Book> sortBookByYear() {

        BookDB.books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear()- o1.getYear();
            }
        });
        return BookDB.books;
    }

    @Override
    public List<Book> searchByName(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book :BookDB.books){
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                result.add(book);
            }
        }

        return result;
    }

    @Override
    public List<Book> getBooksInRangeYear(int startYear, int endYear) {
        List<Book> result = new ArrayList<>();
        for (Book book :BookDB.books){
            if (book.getYear() >= startYear && book.getYear() <= endYear){
                result.add(book);
            }
        }
        return result;
    }
}
