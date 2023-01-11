package ru.pangaia.example.bookstore.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.BookCollection;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookCollectionRepository;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookCollectionRepository bookCollectionRepository;

    @GetMapping(value = "/users/")
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{userId}/")
    User getUser(@PathVariable Long userId) {
        return userRepository.getOne(userId);

    }

    @RequestMapping(value = "/user/{userId}/books/", method = RequestMethod.GET)
    Collection<BookBase> getAllBooksFromUser(@PathVariable Long userId) {
        User user = userRepository.getOne(userId);
        return user.getBooksOwned();
    }

    @PostMapping(value = "/users/")
    User createUser(@RequestBody User user) {
        userRepository.saveAndFlush(user);
        return user;
    }

    @PostMapping("/user/{userId}/")
    User updateUser(@PathVariable Long userId, @RequestBody User userNew) {
        User user = userRepository.getOne(userId);
        user.update(userNew);
        userRepository.saveAndFlush(user);
        return user;
    }

    @PostMapping("/user/{userId}/collections/{collId}/")
    User updateCollectionWithBookIds(
            @PathVariable Long userId,
            @PathVariable Long collId,
            @RequestBody List<Long> bookIds
    ) {
        User user = userRepository.getOne(userId);
        List<BookBase> books = bookRepository.findAllById(bookIds);
        Optional<BookCollection> coll = user.getBookCollections().stream().filter((c) -> c.getId().equals(collId)).findFirst();
        if (coll.isPresent()) {
            books.forEach(coll.get()::addBook);
            userRepository.saveAndFlush(user);
        }
        return user;
    }

    @PostMapping("/user/{userId}/collections/")
    BookCollection createCollectionForUser(
            @PathVariable Long userId,
            @RequestBody BookCollection coll
    ) {
        coll.clear();
        User user = userRepository.getOne(userId);
        user.addCollection(coll);
        bookCollectionRepository.save(coll);
        return coll;
    }

    @DeleteMapping("/user/{userId}/")
    User deleteUser(@PathVariable Long userId) {
        User user = userRepository.getOne(userId);
        userRepository.delete(user);
        return user;
    }

    @DeleteMapping("/user/{userId}/collections/{collId}/")
    User deleteCollectionForUser(@PathVariable Long userId, @PathVariable Long collId) {
        User user = userRepository.getOne(userId);
        Optional<BookCollection> collection = user.getBookCollections().stream().filter((c) -> collId.equals(c.getId())).findFirst();
        if (collection.isPresent()) {
            user.getBookCollections().remove(collection.get());
            userRepository.saveAndFlush(user);
            bookCollectionRepository.delete(collection.get());
        }
        return user;
    }
}
