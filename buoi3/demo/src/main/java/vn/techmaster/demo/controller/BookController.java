package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.service.BookService;

import java.util.*;



//RestController : được sử dụng với các API trả về dữ liệu dạng JSON,XML,...

//@Controller : Được sử dụng với các API trả về giao diện (template), ngoài ra cũng có thể trả về dữ liệu dạng JSON,XML,...

//@RestController = @Controller + @ResponseBody

//@ResponseEntity<T> : Class đại diện cho 1 HTTP response, cho phép bạn cấu hình status code, headers,body của response


@Controller
@RequestMapping("/books") //Khi viết requestMapping thì bên trong chỉ cần thêm các / tiếp theo
public class BookController {
//    Http method : GET, POST, PUT, DELETE.
//    API URL
//    Request body : Thông tin client gửi lên.
//    Response  : Thông tin phan hồi server -> client.



//    private final List<Book> books;
//    public BookController() {
//        books = new ArrayList<>();
//        books.add(new Book(1, "Gone with the wind", "Cuong", 1945));
//        books.add(new Book(2, "Chi Dau", "Nam Cao", 1943));
//    }
//
//    @GetMapping("/books")  //http://localhost:8080/books
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public List<Book> getAllBooks(){
//        return books;
//    }



  @Autowired
  private BookService bookService;

    @GetMapping // Lấy link để lấy toàn bộ số sách
    public ResponseEntity<List<Book>> getAllBook(){
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.CREATED);
    }


    // SOLID
    @GetMapping("/{id}") //http://localhost:8080/books/id
    public ResponseEntity<Book> getBookById(@PathVariable int id){
       Book book = bookService.getBookById(id);
       if (book != null){
         return ResponseEntity.ok(book);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
    @GetMapping("/sortByYear")

    public ResponseEntity<List<Book>> sortByYear(){
//        books.sort(new Comparator<Book>() {
//            @Override
//            public int compare(Book o1, Book o2) {
//                return o2.getYear()-o1.getYear();
//            }
//        });
        return ResponseEntity.ok(bookService.sortBookByYear());
    }
//
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> searchByName(@PathVariable String keyword){
//        List<Book> r = new ArrayList<>();
//        for (Book book :books){
//            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
//                r.add(book);
//            }
        List<Book> book = bookService.searchByName(keyword);
        if (book != null){
            return ResponseEntity.ok(book);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//
        @GetMapping("/startYear/{startYear}/endYear/{endYear}")
    public ResponseEntity<List<Book>> getBooksInRangeYear(@PathVariable int startYear,@PathVariable int endYear){
//        List<Book> r = new ArrayList<>();
//        for (Book book :books){
//            if (book.getYear() >= startYear && book.getYear() <= endYear){
//                r.add(book);
//            }
//        }
        List<Book> book = bookService.getBooksInRangeYear(startYear,endYear);
        if (book != null){
            return ResponseEntity.ok(book);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
