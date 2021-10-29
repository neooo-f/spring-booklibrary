package booklibrary.controller;

import booklibrary.entities.Borrow;
import booklibrary.entities.User;
import booklibrary.models.BorrowsDTO;
import booklibrary.services.UserService;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/")
    List<User> all(@RequestParam(required = false) String firstName, @RequestParam(required = false) String limit) {
        if (limit == null) {
            return userService.getAllUsers();
        } else return userService.getLimitAmountOfUsers(limit, firstName);
    }

    @PostMapping("/")
    User newUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    // Single item

    @GetMapping("/{id}")
    User one(@PathVariable UUID id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable UUID id) {
        User user = userService.getUser(id);
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setAddress(newUser.getAddress());
        user.setGender(newUser.getGender());
        user.setAge(newUser.getAge());
        return userService.updateUser(newUser);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }


    // Borrows Endpoint

    @PostMapping("/{userId}/borrows/books/{bookId}")
    Borrow newBorrow(@PathVariable UUID userId,@PathVariable UUID bookId,@RequestBody BorrowsDTO borrowsDTO) {
        return userService.saveBorrow(userId, bookId, borrowsDTO);
    }
        //Get All Borrows from one User
    @GetMapping("/{userId}/borrows")
    List<Borrow> all(@PathVariable UUID userId) {
        return userService.getAllBorrows(userId);
    }

         //Single Item (Get one selected borrow)
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
    @PatchMapping("/{userId}/borrows/{borrowId}") //noch mit Janis anschauen (Dupliziert Eintrag in DB)
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
