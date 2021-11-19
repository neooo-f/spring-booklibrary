package booklibrary.repositories;

import booklibrary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAllByFirstName(String firstName);
    Optional<User> findByUsername(String username);

}
