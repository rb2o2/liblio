package ru.pangaia.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.service.BookService;

import java.util.List;

@Controller
public class BooksController
{
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/books/")
    String books(Model model)
    {
        List<BookBase> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }
    @GetMapping("/book/{bookId}")
    String book(@PathVariable Long bookId, Model model)
    {
        BookBase book = bookRepository.getOne(bookId);
        model.addAttribute("book", book);
        return "bookDetails";
    }
    @PostMapping("/books/")
    String addBook(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "title") String title)
    {
        BookBase book = new BookBase(author, title);
        bookRepository.saveAndFlush(book);
        return "redirect:/books/";
    }
    @PostMapping("/books/delete")
    String deleteBook(@RequestParam("id") Long bookId)
    {
        bookService.deleteBookById(bookId);
        return "redirect:/books/";
    }
}
