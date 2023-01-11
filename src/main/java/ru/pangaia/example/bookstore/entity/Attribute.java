package ru.pangaia.example.bookstore.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Attribute extends BaseEntity implements Serializable {
    public Attribute() {}
    private static final long serialVersionUID = 1L;

    public String name;

    public String description;
}
