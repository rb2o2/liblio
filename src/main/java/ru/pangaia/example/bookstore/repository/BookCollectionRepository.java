package ru.pangaia.example.bookstore.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import ru.pangaia.example.bookstore.entity.BookCollection;

import javax.persistence.EntityManager;

@Repository
public class BookCollectionRepository extends SimpleJpaRepository<BookCollection, Long> {
    public BookCollectionRepository(EntityManager em) {
        super(BookCollection.class, em);
    }
}
