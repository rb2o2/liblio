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
    public long id;
    public String author;
    public String title;

    public BookBase(String author, @NotNull String title)
    {
        this.author = author;
        this.title = title;
    }
    public BookBase()
    {}
    public void update(BookBase other)
    {
        author = other.author == null? author : other.author;
        title = other.title == null? title: other.title;
    }
    @Override
    public String toString()
    {
        return "[book] id: " + id + "; author: " + author + "; title: " + title;
    }

}
