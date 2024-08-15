package ru.pangaia.example.bookstore.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void deleteBookById(Long id) {
        BookBase book = bookRepository.findById(id).orElseThrow();
        logger.warn(book.toString());
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach((u) ->
        {
            u.getBookCollections().forEach((c) -> {logger.info("" + c.books.stream().anyMatch((b) -> b == book)); c.removeBook(book);});
//            u.booksOwned = u.booksOwned.stream().filter((b) -> !id.equals(b.getId())).collect(Collectors.toSet());
            u.getBooksOwned().remove(book);

        }); //TODO WAY TOO SLOW!
        bookRepository.delete(book);
        userRepository.saveAll(allUsers);
    }

    @Transactional
    public void deleteBookFromUserByIds(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow();
        BookBase book = bookRepository.findById(bookId).orElseThrow();
        bookRepository.delete(book);
        user.getBooksOwned().remove(book);
        user.getBookCollections().forEach((c) -> c.removeBook(book));
        userRepository.saveAndFlush(user);
    }
}
