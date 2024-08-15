package ru.pangaia.example.bookstore.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import ru.pangaia.example.bookstore.entity.BookCollection;

@Repository
public class BookCollectionRepository extends SimpleJpaRepository<BookCollection, Long> {
    public BookCollectionRepository(EntityManager em) {
        super(BookCollection.class, em);
    }
}
