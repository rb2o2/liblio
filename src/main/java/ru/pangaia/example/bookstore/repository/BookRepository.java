package ru.pangaia.example.bookstore.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import ru.pangaia.example.bookstore.entity.BookBase;

import javax.persistence.EntityManager;

@Repository
public class BookRepository extends SimpleJpaRepository<BookBase, Long>
{
    public BookRepository(EntityManager em)
    {
        super(BookBase.class, em);
    }
}
