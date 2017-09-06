package ru.pangaia.example.bookstore.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.BookCollection;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;
import ru.pangaia.example.bookstore.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController
{
    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/books/")
    List<BookBase> getAllBooks()
    {
        List<BookBase> resultSet = bookRepository.findAll();
        return resultSet;
    }

    @GetMapping(value = "/book/{bookId}/")
    BookBase getBook(@PathVariable Long bookId)
    {
        BookBase result = bookRepository.getOne(bookId);
        return result;
    }

    @PostMapping(value = "/book/{bookId}/")
    BookBase updateBook(@PathVariable Long bookId, @RequestBody BookBase bookNew)
    {
        BookBase book  = bookRepository.getOne(bookId);
        book.update(bookNew);
        bookRepository.saveAndFlush(book);
        return book;
    }

    @PostMapping("/books/")
    List<BookBase> createBooks(@RequestBody List<BookBase> books)
    {
        bookRepository.saveAll(books);
        return books;
    }

    @PostMapping("/user/{userId}/books/")
    BookBase createBookForUser(@PathVariable Long userId, @RequestBody BookBase book)
    {
        bookRepository.saveAndFlush(book);
        User user = userRepository.getOne(userId);
        user.addBook(book);
        userRepository.saveAndFlush(user);
        return book;
    }

    @DeleteMapping("/book/{bookId}/")
    void deleteBook(@PathVariable Long bookId)
    {
        bookService.deleteBookById(bookId);
    }

    @DeleteMapping("/user/{userId}/books/{bookId}/")
    void deleteBook(@PathVariable Long userId, @PathVariable Long bookId)
    {
        bookService.deleteBookFromUserByIds(userId, bookId);
    }
}
