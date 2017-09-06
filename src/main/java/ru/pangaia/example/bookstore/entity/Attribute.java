package ru.pangaia.example.bookstore.entity;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Attribute extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String name;

    public String description;

    @ManyToOne
    @Nullable
    public Attribute parent;
}
