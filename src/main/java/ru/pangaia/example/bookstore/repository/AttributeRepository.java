package ru.pangaia.example.bookstore.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import ru.pangaia.example.bookstore.entity.Attribute;

import javax.persistence.EntityManager;

@Repository
public class AttributeRepository extends SimpleJpaRepository<Attribute, Long> {
    public AttributeRepository(EntityManager em) {
        super(Attribute.class, em);
    }
}
