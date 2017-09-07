package ru.pangaia.example.bookstore.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.pangaia.example.bookstore.entity.Attribute;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AttributeRepository extends SimpleJpaRepository<Attribute, Long>
{
    public AttributeRepository(EntityManager em)
    {
        super(Attribute.class, em);
    }

    /*@Transactional
    public List<Attribute> findByNameIsLike(String substring)
    {
        return findAll()
                .stream()
                .filter((cat) -> cat.name.indexOf(substring) > 0)
                .collect(Collectors.toList());
    }*/

    /*@Transactional
    public List<Attribute> findByParent(Attribute parent)
    {
        return findAll()
                .stream()
                .filter((cat) -> cat.parent == parent)
                .collect(Collectors.toList());
    }*/
}
