package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
//@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class Category extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String name;

    public String description;

    @ManyToOne
    @Nullable
    public Category parent;
}
