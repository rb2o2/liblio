package ru.pangaia.example.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public void deleteBookById(Long id)
    {
        BookBase book = bookRepository.getOne(id);
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach((u) ->
        {
            u.bookCollections.forEach((c) -> c.removeBook(book));
            u.booksOwned.remove(book);

        }); //TODO WAY TOO SLOW!
        userRepository.saveAll(allUsers);
        bookRepository.delete(book);
    }

    public void deleteBookFromUserByIds(Long userId, Long bookId)
    {
        User user = userRepository.getOne(userId);
        BookBase book = bookRepository.getOne(bookId);
        user.booksOwned.remove(book);
        user.bookCollections.forEach((c) -> c.removeBook(book));
        bookRepository.delete(book);
        userRepository.saveAndFlush(user);
    }
}
