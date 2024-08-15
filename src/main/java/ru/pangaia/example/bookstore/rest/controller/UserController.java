package ru.pangaia.example.bookstore.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.BookCollection;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookCollectionRepository;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final BookCollectionRepository bookCollectionRepository;

    @GetMapping(value = "/users/")
    List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{userId}/")
    User getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow();

    }

    @RequestMapping(value = "/user/{userId}/books/", method = RequestMethod.GET)
    Collection<BookBase> getAllBooksFromUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getBooksOwned();
    }

    @PostMapping(value = "/users/")
    User createUser(@RequestBody User user) {
        userRepository.saveAndFlush(user);
        return user;
    }

    @PostMapping("/user/{userId}/")
    User updateUser(@PathVariable Long userId, @RequestBody User userNew) {
        User user = userRepository.findById(userId).orElseThrow();
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
        User user = userRepository.findById(userId).orElseThrow();
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
        User user = userRepository.findById(userId).orElseThrow();
        user.addCollection(coll);
        bookCollectionRepository.save(coll);
        return coll;
    }

    @DeleteMapping("/user/{userId}/")
    User deleteUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);
        return user;
    }

    @DeleteMapping("/user/{userId}/collections/{collId}/")
    User deleteCollectionForUser(@PathVariable Long userId, @PathVariable Long collId) {
        User user = userRepository.findById(userId).orElseThrow();
        Optional<BookCollection> collection = user.getBookCollections().stream().filter((c) -> collId.equals(c.getId())).findFirst();
        if (collection.isPresent()) {
            user.getBookCollections().remove(collection.get());
            userRepository.saveAndFlush(user);
            bookCollectionRepository.delete(collection.get());
        }
        return user;
    }
}
