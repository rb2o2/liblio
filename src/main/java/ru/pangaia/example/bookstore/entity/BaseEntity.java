package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@JsonIgnoreProperties({"userCreated", "userModified"})
public abstract class BaseEntity
{
    @Id
    @GeneratedValue
    private long id;

    @CreatedDate
    private Timestamp dateCreated;

    @LastModifiedDate
    private Timestamp dateModified;

    @ManyToOne
    private User userCreated;

    @ManyToOne
    private User userModified;

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

    public Timestamp getDateCreated()
    {
        return dateCreated;
    }
}
