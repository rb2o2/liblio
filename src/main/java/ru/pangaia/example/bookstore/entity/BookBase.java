package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class BookBase implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    private String author;
    private String title;

    public BookBase(@NotNull String author, @NotNull String title)
    {
        this.author = author;
        this.title = title;
    }
    public BookBase()
    {}
    @Override
    public String toString()
    {
        return "[book] id: " + id + "; author: " + author + "; title: " + title;
    }

}
