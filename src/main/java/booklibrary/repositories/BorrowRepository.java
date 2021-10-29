package booklibrary.repositories;

import booklibrary.entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    List<Borrow> findAllByUserId(UUID userId);

    @Transactional
    void deleteAllByUserId(UUID userId);

}
