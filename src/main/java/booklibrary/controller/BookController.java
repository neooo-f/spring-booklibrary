package booklibrary.controller;

import java.util.List;
import java.util.UUID;
import booklibrary.entities.Book;
import booklibrary.services.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Get All Books
    @GetMapping("/books")
    List<Book> all() {
        return bookService.getAllBooks();
    }

    //Create One Book
    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return bookService.saveBook(newBook);
    }

    //Get One Book
    @GetMapping("/books/{id}")
    Book one(@PathVariable UUID id) {
        return bookService.getBook(id);
    }

    // Update One Book
    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable UUID id) {
        Book book = bookService.getBook(id);
        book.setAuthor(newBook.getAuthor());
        book.setReleaseYear(newBook.getReleaseYear());
        book.setTitle(newBook.getTitle());
        return bookService.updateBook(newBook);
    }

    //Delete One Book
    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
    }

}
