package booklibrary.services;

import booklibrary.entities.Book;
import java.util.List;
import java.util.UUID;

import booklibrary.exceptions.BookNotFoundException;
import booklibrary.repositories.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    public final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Book getBook(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book updateBook(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

}
