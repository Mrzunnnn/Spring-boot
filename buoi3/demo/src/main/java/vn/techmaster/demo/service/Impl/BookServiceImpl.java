package vn.techmaster.demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.demo.dao.BookDAO;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.service.BookService;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;
    @Override
    public List<Book> getAllBook() {
        return bookDAO.findALl();
    }

    @Override
    public Book getBookById(int id) {

//         //Cách 1 : Tìm gián tiếp
//        List<Book> books = bookDAO.findALl();
//        for (Book book :books){
//            if (book.getId()==id){
//                return book;
//            }
//        }return null;
        // Cách 2 : Tìm trực tiếp trong DB
        return bookDAO.findId(id);
    }

    @Override
    public List<Book> sortBookByYear() {
        return bookDAO.sortBookByYear();
    }

    @Override
    public List<Book> searchByName(String keyword) {
        return bookDAO.searchByName(keyword);
    }

    @Override
    public List<Book> getBooksInRangeYear(int startYear, int endYear) {
        return bookDAO.getBooksInRangeYear(startYear,endYear);
    }
}
