package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
public abstract class BaseEntity
{
    @Id
    @GeneratedValue
    @JsonProperty
    private long id;

    @JsonProperty
    private final Timestamp dateCreated;

    private Timestamp dateModified;

    @ManyToOne
    private User userCreated;

    @ManyToOne
    private User userModified;

    public BaseEntity()
    {
        dateCreated = Timestamp.from(Instant.now());
    }

    public User getUserCreated()
    {
        return User.getDefaultUser();
    }

    public User getUserModified()
    {
        return User.getDefaultUser();
    }

    public Long getId()
    {
        return id;
    }

    public Timestamp getDateModified()
    {
        return dateModified;
    }

    protected void setDateModified(Timestamp dateModified)
    {
        this.dateModified = dateModified;
    }


    public Timestamp getDateCreated()
    {
        return dateCreated;
    }
}
