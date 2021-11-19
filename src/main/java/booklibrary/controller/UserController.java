package booklibrary.controller;

import booklibrary.entities.Borrow;
import booklibrary.entities.User;
import booklibrary.models.BorrowsDTO;
import booklibrary.models.UsersRequestDTO;
import booklibrary.models.UsersResponseDTO;
import booklibrary.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    //Get All Users (with RequestParams)
    @GetMapping
    List<User> all(@RequestParam(required = false) String firstName, @RequestParam(required = false) String limit) {
        if (limit == null) {
            return userService.getAllUsers();
        } else return userService.getLimitAmountOfUsers(limit, firstName);
    }

    //Create One User
    @PostMapping
    UsersResponseDTO newUser(@RequestBody UsersRequestDTO usersRequestDTO) {
        return userService.saveUser(usersRequestDTO);
    }

    //Get One User
    @GetMapping("/{id}")
    User one(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    //Update One User
    @PutMapping("/{id}")
    UsersResponseDTO replaceUser(@RequestBody UsersRequestDTO newUser, @PathVariable UUID id) {
        return userService.updateUser(newUser, id);
    }

    //Delete One User
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }


    /* Borrows Endpoint */

    //Create a new Borrow
    @PostMapping("/{userId}/borrows/books/{bookId}")
    Borrow newBorrow(@PathVariable UUID userId,@PathVariable UUID bookId,@RequestBody BorrowsDTO borrowsDTO) {
        return userService.saveBorrow(userId, bookId, borrowsDTO);
    }

    //Get All Borrows from one User
    @GetMapping("/{userId}/borrows")
    List<Borrow> all(@PathVariable UUID userId) {
        return userService.getAllBorrows(userId);
    }

    //Get One Borrow
    @GetMapping("/{userId}/borrows/{borrowId}")
    Borrow one(@PathVariable UUID userId, @PathVariable UUID borrowId) {
        return  userService.getBorrow(borrowId);
    }

    //Delete One Borrow
    @DeleteMapping("/{userId}/borrows/{borrowId}")
    void deleteBorrow(@PathVariable UUID userId, @PathVariable UUID borrowId) {
        userService.deleteBorrow(borrowId);
    }

    //Update the Date of One Borrow
    @PatchMapping("/{userId}/borrows/{borrowId}")
    Borrow replaceBorrow(@RequestBody BorrowsDTO borrowsDTO, @PathVariable UUID borrowId) {
        Borrow borrow = userService.getBorrow(borrowId);
        borrow.setStartDate(borrowsDTO.getStartDate());
        borrow.setEndDate(borrowsDTO.getEndDate());
        return userService.updateBorrow(borrow);
    }

    //Delete All Borrows from a User
    @DeleteMapping("/{userId}/borrows")
    void deleteAllBorrows(@PathVariable UUID userId) {
        userService.deleteAllBorrows(userId);
    }

}
