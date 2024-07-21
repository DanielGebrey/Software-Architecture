package books;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "books.Repository")
@EnableJms
public class BookApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
