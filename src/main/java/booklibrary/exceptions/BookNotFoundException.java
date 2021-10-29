package booklibrary.exceptions;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(UUID id) {
        super("Could not find book " + id);
    }
}
