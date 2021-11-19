package booklibrary;

import booklibrary.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class BooklibraryApplication {

	public static void main(String... args) {
		SpringApplication.run(BooklibraryApplication.class, args);
	}

}
