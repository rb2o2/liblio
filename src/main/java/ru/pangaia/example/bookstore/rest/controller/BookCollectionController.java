package ru.pangaia.example.bookstore.rest.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pangaia.example.bookstore.entity.BookCollection;
import ru.pangaia.example.bookstore.repository.BookCollectionRepository;
import ru.pangaia.example.bookstore.repository.BookRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BookCollectionController {
    private final BookCollectionRepository bookCollectionRepository;

    private final BookRepository bookRepository;

    @GetMapping("/collections/")
    List<BookCollection> getAllCollections() {
        return bookCollectionRepository.findAll();
    }

    @GetMapping("/collection/{collId}/")
    BookCollection getBookCollection(@PathVariable Long collId) {
        return bookCollectionRepository.findById(collId).orElseThrow();
    }
}

