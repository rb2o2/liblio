package ru.pangaia.example.bookstore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController
{
    @Autowired
    ApplicationContext ctx;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/books/", method = RequestMethod.GET)
    List<BookBase> getAllBooks()
    {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/user/{userId}/books/", method = RequestMethod.GET)
    List<BookBase> getAllBooksFromUser(@PathVariable Long userId)
    {
        User user = userRepository.getOne(userId);
        return user.getBooksOwned();
    }
    @RequestMapping(value = "/book/{bookId}/", method = RequestMethod.GET)
    BookBase getBook(@PathVariable Long bookId)
    {
        return bookRepository.getOne(bookId);
    }
    @RequestMapping(value = "/user/{userId}/books/create", method = RequestMethod.POST)
    BookBase createBookForUser(@PathVariable Long userId, @RequestBody BookBase book)
    {
        bookRepository.saveAndFlush(book);
        User user = userRepository.getOne(userId);
        user.addBook(book);
        userRepository.saveAndFlush(user);
        return book;
    }
}
