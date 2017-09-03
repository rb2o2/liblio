package ru.pangaia.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.repository.BookRepository;

import java.util.List;

@Controller
public class BooksController
{
    @Autowired
    BookRepository bookRepository;
    @GetMapping("/books")
    String getAllBooks(Model model)
    {
        List<BookBase> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }
}
