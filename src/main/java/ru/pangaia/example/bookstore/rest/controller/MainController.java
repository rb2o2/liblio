package ru.pangaia.example.bookstore.rest.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pangaia.example.bookstore.repository.AttributeRepository;
import ru.pangaia.example.bookstore.repository.BookRepository;
import ru.pangaia.example.bookstore.repository.UserRepository;


@RestController
public class MainController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AttributeRepository categoryRepository;

    Logger logger;

    private static final String STORE_ADDR_DIR = "/home/oneuro/.bookshelfData/data.odb";

    @RequestMapping("/api/cleanData")
    public String cleanData() {
        userRepository.deleteAll();
        bookRepository.deleteAll();
        return "DB Cleared";
    }

//    @GetMapping("/api/initCategories")
//    public String initCategories()
//    {
//        categoryRepository.deleteAll();
//
//        Attribute catRoot = new Attribute();
//        catRoot.name = "_ROOT";
//        categoryRepository.saveAndFlush(catRoot);
//
//        List<Pair<String, String>> level1 = Arrays.asList(
//                Pair.of("Худлит", "Художественная литература"),
//                Pair.of("Для детей", "Детская литература"),
//                Pair.of("Учеба", "Учебники и методички для школы и ВУЗов"),
//                Pair.of("Справочники", "Энциклопедии, справочники..."),
//                Pair.of("Искусство", "Альбомы, пособия..."),
//                Pair.of("Языки", "Словари, учебники по иностранным языкам..."),
//                Pair.of("Наука", "Научная литература, научно-популярное"),
//                Pair.of("Публицистика", "публицистика, философия..."),
//                Pair.of("Отдых", "Развлечения, хобби, рукоделие, DIY..."),
//                Pair.of("Быт", "Кухня, огород... "),
//                Pair.of("ЗОЖ", "Медицина, спорт..."),
//                Pair.of("Духовное", "Религия, эзотерика, мистика..."));
//
//        level1.forEach((p) ->
//        {
//            Attribute cat = new Attribute();
//            cat.name = p.getFirst();
//            cat.description = p.getSecond();
//            cat.parent = catRoot;
//            categoryRepository.saveAndFlush(cat);
//        });
//        return "Categories initialized";
//    }

    @GetMapping("/api/clearCategories")
    public String clearCategories() {
        categoryRepository.deleteAll();
        return "categories cleared";
    }
}
