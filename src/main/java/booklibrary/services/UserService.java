package booklibrary.services;

import booklibrary.entities.Book;
import booklibrary.entities.Borrow;
import booklibrary.entities.User;
import booklibrary.exceptions.BookNotFoundException;
import booklibrary.exceptions.BorrowNotFoundException;
import booklibrary.exceptions.UserNotFoundException;
import booklibrary.models.BorrowsDTO;
import booklibrary.repositories.BookRepository;
import booklibrary.repositories.BorrowRepository;
import booklibrary.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

        //Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getLimitAmountOfUsers(String limit, String firstName) { // Query Parameters
        return userRepository
                .findAllByFirstName(firstName)
                .stream()
                .limit(Integer.parseInt(limit))
                .collect(Collectors.toList());
    }

        // Save a new User
    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

        // Get one User
    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    //Borrows
    public Borrow saveBorrow(UUID userId, UUID bookId, BorrowsDTO borrowsDTO) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        return borrowRepository.save(new Borrow(user, book, borrowsDTO.getStartDate(), borrowsDTO.getEndDate()));
    }

    public List<Borrow> getAllBorrows(UUID userId) {
        return borrowRepository.findAllByUserId(userId);
    }

    public Borrow getBorrow(UUID borrowId) {
        return borrowRepository.findById(borrowId)
                .orElseThrow(() -> new BorrowNotFoundException(borrowId));
    }

    public Borrow updateBorrow(Borrow updatedBorrow) {
        return borrowRepository.save(updatedBorrow);
    }

        //Delete One Borrow
    public void deleteBorrow(UUID borrowId) {
        borrowRepository.deleteById(borrowId);
    }

        // Delete All Borrows from one User
    public void deleteAllBorrows(UUID userId) {
        borrowRepository.deleteAllByUserId(userId); //Does Not work? java.util.UUID cannot be converted to java.lang.Iterable<?
    }


}
