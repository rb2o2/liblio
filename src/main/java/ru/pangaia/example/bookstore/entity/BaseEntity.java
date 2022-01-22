package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@JsonIgnoreProperties({"userCreated", "userModified"})
public abstract class BaseEntity {
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
