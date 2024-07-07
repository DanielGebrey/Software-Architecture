package books.Controller;

import books.Model.Book;
import books.Repository.BookRepo;
import books.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }
    @PutMapping
    public ResponseEntity<Book> update(@RequestBody Book book){
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted");
    }
}
