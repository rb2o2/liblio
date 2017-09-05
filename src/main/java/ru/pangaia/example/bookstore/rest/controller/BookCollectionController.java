package ru.pangaia.example.bookstore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pangaia.example.bookstore.entity.BookCollection;
import ru.pangaia.example.bookstore.repository.BookCollectionRepository;
import ru.pangaia.example.bookstore.repository.BookRepository;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookCollectionController
{
    @Autowired
    BookCollectionRepository bookCollectionRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/collections/")
    List<BookCollection> getAllCollections()
    {
        List<BookCollection> resultSet = bookCollectionRepository.findAll();
        return resultSet;
    }

    @GetMapping("/collection/{collId}/")
    BookCollection getBookCollection(@PathVariable Long collId)
    {
        BookCollection result = bookCollectionRepository.getOne(collId);
        return result;
    }
}
