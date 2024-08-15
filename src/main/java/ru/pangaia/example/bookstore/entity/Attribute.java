package ru.pangaia.example.bookstore.entity;

import jakarta.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@Entity
public class Attribute extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public String name;
    public String description;

    public Attribute() {}
}
