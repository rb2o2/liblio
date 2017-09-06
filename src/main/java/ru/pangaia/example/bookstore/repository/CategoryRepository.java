package ru.pangaia.example.bookstore.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pangaia.example.bookstore.entity.Category;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryRepository extends SimpleJpaRepository<Category, Long>
{
    public CategoryRepository(EntityManager em)
    {
        super(Category.class, em);
    }

    @Transactional
    public List<Category> findByNameIsLike(String substring)
    {
        return findAll()
                .stream()
                .filter((cat) -> cat.name.indexOf(substring) > 0)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Category> findByParent(Category parent)
    {
        return findAll()
                .stream()
                .filter((cat) -> cat.parent == parent)
                .collect(Collectors.toList());
    }
}
