/* package booklibrary.controller;

import booklibrary.entities.Borrow;
import booklibrary.services.BorrowService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class BorrowController {
   // - GetMapping -> Alle laufenden Ausleihungen
   // - PostMapping -> Ein noch nicht besetztes Buch ausleihen
   // - GetMapping (Single Item) -> Eine laufende Ausleihung anhand der UUID betrachten.
   // - PutMapping -> Ausleihung anpassen
   // - DeleteMapping -> Ausleihung löschen

    private BorrowService borrowService;

    BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/borrows")
    List<Borrow> all() {
        return borrowService.getAllBorrows();
    }

    @PostMapping("/borrows") //UUID von Book mitgeben, überprüfen ob das Buch gerade ausgelehnt wird
    Borrow newBorrow(@RequestBody Borrow newBorrow) { //von wo weis Java, welche id dies ist? (3 entities)
        return borrowService.saveBorrow(newBorrow);
        // Auf diesen Endpoint nacher das Login?
    }

    // Single item

    @GetMapping("/borrows/{id}")
    Borrow one(@PathVariable UUID id) {
        return borrowService.getBorrow(id);
    }

    // @PutMapping

    @DeleteMapping("/borrows/{id}")
    void deleteBook(@PathVariable UUID id) {
        borrowService.deleteBorrow(id);
    }
} */
