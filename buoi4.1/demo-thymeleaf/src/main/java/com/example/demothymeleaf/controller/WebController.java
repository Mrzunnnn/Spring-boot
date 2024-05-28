package com.example.demothymeleaf.controller;

import com.example.demothymeleaf.model.Book;
import com.example.demothymeleaf.model.PageResponse;
import com.example.demothymeleaf.model.PageResponseimpl;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private final List<Book> books;

    public WebController() {
        books = new ArrayList<>();
        books.add(new Book(1, "Gone with the wind", "Cuong", 1945));
        books.add(new Book(2, "Chi Dau", "Nam Cao", 1943));
        books.add(new Book(3, "The old", "Nguyen Thi C", 1978));
        books.add(new Book(4, "Nha gia kim", "Tran Van B", 1927));
        books.add(new Book(5, "So do", "Cuong", 1937));
        books.add(new Book(6, "Chien thang DBP", "Cuong", 1957));
        books.add(new Book(7, "Truyen kieu", "Cuong", 1977));
        books.add(new Book(8, "Truyen kieu2", "Cuong", 1979));
        books.add(new Book(9, "Tuyen ngon", "Cuong", 1980));
        books.add(new Book(10, "Sach A", "Cuong", 1980));
        books.add(new Book(11, "Sach B ", "Cuong", 1923));
        books.add(new Book(12, "Sach V", "Nguyen Thi C", 1944));
        books.add(new Book(13, "Sach D", "Cuong", 1967));
        books.add(new Book(14, "Sach G", "Nguyen Thi C", 1988));
        books.add(new Book(15, "Sach S", "Tran Van B", 1925));
        books.add(new Book(16, "Sach T", "Tran Van B", 1975));
        books.add(new Book(17, "Sach N", "Nam Cao", 1928));
        books.add(new Book(18, "Sach C", "Nguyen Thi C", 1985));
        books.add(new Book(19, "Sach Y", "Nam Cao", 1980));
        books.add(new Book(20, "Sach Ư", "Nguyen Thi C", 1944));
        books.add(new Book(21, "Sach Q", "Nam Cao", 1980));
        books.add(new Book(22, "Sach I", "Tran Van B", 1980));


    }
    /*
     * Dữ liệu trên 1 trang
     * Có bao nhiêu item trên 1 trang
     * tổng sô trang
     * Tong So phần tử
     * Trang hiện tại
     * Trang đầu tiên
     * Trang cuối cùng
     *
     */

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("book", books.get(0));
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/books")
    public String getBookList(Model model, @RequestParam(required = false, defaultValue = "1") int page
            ,@RequestParam(required = false, defaultValue = "5") int pageSize) {
        PageResponse<Book> pageData = new PageResponseimpl<>(books,page,pageSize);
        model.addAttribute("pageData",pageData);

        return "book-list";
    }

    @GetMapping("/blog")
    public String getBlog() {
        return "admin/blog";
    }

    @GetMapping("/books/{id}")
    public String getBookDeetail(Model model, @PathVariable int id) {
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("book", book);
        if (book != null) {
            List<Book> relateBook =
                    books.stream()
                            .filter(c -> c.getAuthor().equals(book.getAuthor()) && c.getId() != book.getId())
                            .limit(4)
                            .sorted((a, b) -> b.getYear() - a.getYear())
                            .toList();
            model.addAttribute("relateBook", relateBook);
        } else {
            model.addAttribute("relateBook", new ArrayList<>());
        }
        return "book-detail";
    }
    @GetMapping("/books/search")
    public String getBookSearch(Model model) {
        return "book-searchPage";
    }

    @GetMapping("/books/search/{keyword}")
    public String getBookSearch(Model model, @PathVariable String keyword) {
        Book book = books.stream()
                .filter((b->b.getTitle().toLowerCase().equals(keyword.toLowerCase())))
                .findFirst()
                .orElse(null);
        model.addAttribute("book", book);
        return "searchBook";
    }


}
