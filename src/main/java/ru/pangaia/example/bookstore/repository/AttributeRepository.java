package ru.pangaia.example.bookstore.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import ru.pangaia.example.bookstore.entity.Attribute;

@Repository
public class AttributeRepository extends SimpleJpaRepository<Attribute, Long> {
    public AttributeRepository(EntityManager em) {
        super(Attribute.class, em);
    }
}
