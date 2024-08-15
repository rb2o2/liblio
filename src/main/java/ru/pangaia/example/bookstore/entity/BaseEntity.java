package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@JsonIgnoreProperties({"userCreated", "userModified"})
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    public long id;

    @CreatedDate
    private Timestamp dateCreated;

    @LastModifiedDate
    private Timestamp dateModified;

    @ManyToOne
    @CreatedDate
    private User userCreated;

    @ManyToOne
    @LastModifiedDate
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
