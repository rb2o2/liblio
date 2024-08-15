package ru.pangaia.example.bookstore.rest.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;
import ru.pangaia.example.bookstore.service.BookService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    @GetMapping(value = "/books/")
    List<BookBase> getAllBooks() {
        return  bookRepository.findAll();
    }

    @GetMapping(value = "/book/{bookId}/")
    BookBase getBook(@PathVariable Long bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }

    @PostMapping(value = "/book/{bookId}/")
    BookBase updateBook(@PathVariable Long bookId, @RequestBody BookBase bookNew) {
        BookBase book  = bookRepository.findById(bookId).orElseThrow();
        book.update(bookNew);
        bookRepository.saveAndFlush(book);
        return book;
    }

    @PostMapping("/books/")
    List<BookBase> createBooks(@RequestBody List<BookBase> books) {
        bookRepository.saveAll(books);
        return books;
    }

    @PostMapping("/user/{userId}/books/")
    BookBase createBookForUser(@PathVariable Long userId, @RequestBody BookBase book) {
        bookRepository.saveAndFlush(book);
        User user = userRepository.findById(userId).orElseThrow();
        user.addBook(book);
        userRepository.saveAndFlush(user);
        return book;
    }

    @DeleteMapping("/book/{bookId}/")
    void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }

    @DeleteMapping("/user/{userId}/books/{bookId}/")
    void deleteBook(@PathVariable Long userId, @PathVariable Long bookId) {
        bookService.deleteBookFromUserByIds(userId, bookId);
    }
}
