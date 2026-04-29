package com.example.demo.repository;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllWithAuthor_ShouldReturnBooksWithAuthors() {
        Author author = new Author("Author 1", "Bio");
        authorRepository.save(author);

        Book book = new Book("Book 1", 2023, author);
        bookRepository.save(book);

        List<Book> books = bookRepository.findAllWithAuthor();
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        assertEquals("Author 1", books.get(0).getAuthor().getName());
    }
}
