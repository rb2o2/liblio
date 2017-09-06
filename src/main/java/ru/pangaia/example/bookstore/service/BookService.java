package ru.pangaia.example.bookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void deleteBookById(Long id)
    {
        BookBase book = bookRepository.getOne(id);
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
    public void deleteBookFromUserByIds(Long userId, Long bookId)
    {
        User user = userRepository.getOne(userId);
        BookBase book = bookRepository.getOne(bookId);
        bookRepository.delete(book);
        user.getBooksOwned().remove(book);
        user.getBookCollections().forEach((c) -> c.removeBook(book));
        userRepository.saveAndFlush(user);
    }
}
