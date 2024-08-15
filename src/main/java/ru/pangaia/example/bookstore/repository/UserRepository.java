package ru.pangaia.example.bookstore.repository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import ru.pangaia.example.bookstore.entity.User;

@Repository
public class UserRepository extends SimpleJpaRepository<User, Long> {
    public UserRepository(EntityManager em) {
        super(User.class, em);
    }
}
