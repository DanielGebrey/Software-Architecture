package books.Service;

import books.Model.Book;
import books.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private JmsTemplate jmsTemplate;

 public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }
    public Book getBookById(String id){
     Book book = bookRepo.findById(id).orElse(null);
        return book;
    }
    public Book saveBook(Book book){
        Book savedBook = bookRepo.save(book);
        jmsTemplate.convertAndSend("book_queue",savedBook);
        return savedBook;
    }
    public Book updateBook(Book book){
        Book updatedBook = bookRepo.findById(book.getIsbn()).orElseThrow(()->new RuntimeException(String.format("Cannot find book with isbn %s",book.getIsbn())));
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setTitle(book.getTitle());
        updatedBook.setPrice(book.getPrice());
        bookRepo.save(updatedBook);
        jmsTemplate.convertAndSend("book_queue",updatedBook);
        return updatedBook;
    }
    public void deleteBook(String id){
     Book book = bookRepo.findById(id).orElseThrow(()->new RuntimeException(String.format("Cannot find book with isbn %s",id)));
        bookRepo.delete(book);
        jmsTemplate.convertAndSend("book_queue",book);
}
}
