package booklibrary.services;

import booklibrary.entities.Book;
import booklibrary.entities.Borrow;
import booklibrary.entities.User;
import booklibrary.exceptions.BookNotFoundException;
import booklibrary.exceptions.BorrowNotFoundException;
import booklibrary.exceptions.UserNotFoundException;
import booklibrary.models.BorrowsDTO;
import booklibrary.models.UsersRequestDTO;
import booklibrary.models.UsersResponseDTO;
import booklibrary.repositories.BookRepository;
import booklibrary.repositories.BorrowRepository;
import booklibrary.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BorrowRepository borrowRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
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

    public UsersResponseDTO saveUser(UsersRequestDTO usersRequestDTO) {

        User user = userRepository.save(new User(
                usersRequestDTO.getFirstName(),
                usersRequestDTO.getLastName(),
                usersRequestDTO.getAddress(),
                usersRequestDTO.getGender(),
                usersRequestDTO.getAge(),
                passwordEncoder.encode(usersRequestDTO.getPassword()),
                usersRequestDTO.getUsername(),
                "USER"));

        return new UsersResponseDTO(user);
    }

    // Get One User
    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    //Update User
    public UsersResponseDTO updateUser(UsersRequestDTO newUser, UUID id) {
        User user = getUser(id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setAddress(newUser.getAddress());
        user.setGender(newUser.getGender());
        user.setAge(newUser.getAge());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUsername(newUser.getUsername());
        User updatedUser = userRepository.save(user);

        return new UsersResponseDTO(updatedUser);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    /* Borrows */
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

    public void deleteBorrow(UUID borrowId) {
        borrowRepository.deleteById(borrowId);
    }

    public void deleteAllBorrows(UUID userId) {
        borrowRepository.deleteAllByUserId(userId);
    }


}
