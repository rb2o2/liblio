package ru.pangaia.example.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/","/index.html"})
    public String index()
    {
        return "index";
    }
}
