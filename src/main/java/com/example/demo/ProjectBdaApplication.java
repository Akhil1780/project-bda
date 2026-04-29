package com.example.demo;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectBdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectBdaApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(AuthorRepository authorRepo, BookRepository bookRepo) {
		return args -> {
			if (authorRepo.count() == 0) {
				for (int i = 1; i <= 10; i++) {
					Author a = new Author("Author " + i, "Bio of Author " + i);
					authorRepo.save(a);
					Book b = new Book("Book Title " + i, 2000 + i, a);
					bookRepo.save(b);
				}
			}
		};
	}
}
