package ru.pangaia.example.bookstore.rest.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;


@RestController
public class MainController
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    Logger logger;
    private static final String STORE_ADDR_DIR = "/home/oneuro/.bookshelfData/data.odb";

    @RequestMapping("/api/cleanData")
    public String cleanData()
    {
        userRepository.deleteAll();
        bookRepository.deleteAll();
        return "DB Cleared";
    }
}
