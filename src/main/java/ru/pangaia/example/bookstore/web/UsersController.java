package ru.pangaia.example.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pangaia.example.bookstore.entity.BookBase;
import ru.pangaia.example.bookstore.entity.BookCollection;
import ru.pangaia.example.bookstore.entity.User;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/users/")
    String users(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("location", "users");
        return "users";
    }

    @PostMapping("/users/")
    String addUser(
            @RequestParam("name") String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam("login") String login,
            @RequestParam("password") String password,
            Model model
    ) {
        User user = new User(name, login, email, password);
//        user.name = name;
//        if (email != null)
//        {
//            user.email = email;
//        }
//        user.login = login;
//        user.setPassword(password);
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("location", "users");
        return "users";
    }

    @PostMapping("/users/delete")
    String deleteUser(@RequestParam("id") Long userId) {
        User user = userRepository.getOne(userId);
        userRepository.delete(user);
        return "users";
    }

    @GetMapping("/user/{userId}")
    String user(@PathVariable Long userId, Model model) {
        User user = userRepository.getOne(userId);
        model.addAttribute("user", user);
        model.addAttribute("location", "users");
        return "userDetails";
    }

    @PostMapping("/user/{userId}/collections/")
    String addCollection(@PathVariable Long userId, @RequestParam String collName, Model model) {
        User user = userRepository.getOne(userId);
        BookCollection bookCollection = new BookCollection(collName);
        user.addCollection(bookCollection);
        userRepository.saveAndFlush(user);
        model.addAttribute(user);

        return "redirect:/user/{userId}";
    }

    @GetMapping("/user/{userId}/collection/{collId}/")
    String getCollection(@PathVariable Long userId, @PathVariable Long collId, Model model) {
        User user = userRepository.getOne(userId);
        BookCollection collection = user.getBookCollectionById(collId);
        model.addAttribute("collection", collection);
        model.addAttribute("user", user);
        model.addAttribute("location", "users");

        return "collectionDetails";
    }

    @GetMapping("/user/{userId}/books/add")
    String selectBooksToAddToUser(@PathVariable Long userId, Model model) {
        User user = userRepository.getOne(userId);
        List<BookBase> books = bookRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("collection", null);
        model.addAttribute("books", books);
        model.addAttribute("location", "users");
        return "addBook";
    }

    @PostMapping("/user/{userId}/books/add")
    String addBookToUser(
            @PathVariable Long userId,
            @RequestParam Map<String, String> bookIdPayload,
            Model model
    ) {
        User user = userRepository.getOne(userId);
        List<BookBase> books = bookRepository.findAllById(parseBookIds(bookIdPayload));
        user.addBooks(books);
        userRepository.saveAndFlush(user);
        model.addAttribute("user", user);
        return "redirect:/user/{userId}";

    }

    @GetMapping("/user/{userId}/collection/{cid}/add")
    String selectBooksToAddToCollection(
            @PathVariable Long userId,
            @PathVariable Long cid,
            Model model
    ) {
        User user = userRepository.getOne(userId);
        BookCollection collection = user.getBookCollectionById(cid);
//        List<BookBase> books = new ArrayList<>(user.getBooksOwned());
        model.addAttribute("collection", collection);
        model.addAttribute("user", user);
        model.addAttribute("books", user.getBooksOwned());
        model.addAttribute("location", "users");
        return "addBook";
    }

    @PostMapping("/user/{userId}/collection/{cid}/add")
    String addBookToCollection(
            @PathVariable Long userId,
            @PathVariable Long cid,
            @RequestParam Map<String, String> bookIdPayload,
            Model model
    ) {
        User user = userRepository.getOne(userId);
        List<BookBase> books = bookRepository.findAllById(parseBookIds(bookIdPayload));
        BookCollection coll = user.getBookCollectionById(cid);
        logger.info(coll.name + " <- " + books);
        coll.addAll(books);
        userRepository.saveAndFlush(user);
        model.addAttribute("user", user);
        return "redirect:/user/{userId}";
    }

    private List<Long> parseBookIds(Map<String,String> payload) {
        logPayload(payload);
        List<Long> ids = payload.entrySet().stream()
                .filter((es) -> es.getValue().equals("on"))
                .map((e) -> Long.parseLong(e.getKey().substring(3)))
                .collect(Collectors.toList());
        logger.info(ids.toString());
        return ids;
    }

    private void logPayload(Map<String, String> payload) {
        StringBuilder sb = new StringBuilder();
        payload.forEach((key, value) ->
        {
            sb.append(key);
            sb.append("=");
            sb.append(value);
        });
        logger.info(sb.toString());
    }
}
