package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAllWithAuthor());
        return "list-books";
    }

    @GetMapping("/books/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            return "book-form";
        }
        try {
            bookService.save(book);
        } catch (Exception e) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("errorMessage", "An integrity violation occurred while saving the book.");
            return "book-form";
        }
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.findAll());
        return "book-form";
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            book.setId(id);
            model.addAttribute("authors", authorService.findAll());
            return "book-form";
        }
        try {
            bookService.save(book);
        } catch (Exception e) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("errorMessage", "An integrity violation occurred while updating the book.");
            return "book-form";
        }
        return "redirect:/books";
    }
}
