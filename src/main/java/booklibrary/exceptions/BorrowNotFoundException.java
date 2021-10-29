package booklibrary.exceptions;

import java.util.UUID;

public class BorrowNotFoundException extends RuntimeException {

    public BorrowNotFoundException(UUID id) {
        super("Could not find book " + id);
    }
}
