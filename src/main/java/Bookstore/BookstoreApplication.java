package Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Bookstore.domain.Book;
import Bookstore.domain.BookRepository;


@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("Save some books please");
			repository.save(new Book("Atlas", "Pertti Jarvinen", 2009, "1-754", 9.00));
			repository.save(new Book("Kissojen maailma", "Jertti Parvinen", 2011, "1-756", 9.99));
			
			log.info("Get the books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};

}
}

