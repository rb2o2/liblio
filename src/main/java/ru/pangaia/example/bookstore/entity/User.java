package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.*;

@Entity
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    public long id;


    public String name;

    public String login;

    public String email;

    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Collection<BookBase> booksOwned = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Collection<BookCollection> bookCollections = new HashSet<>();

    public User(String username)
    {
        this.name = username;
        this.login = String.join("_", Arrays.asList(username.split(" "))).toLowerCase();
        this.password = "admin";
    }
    public User()
    {}
    public void setPassword(String password)
    {
        this.password = password;
    }
    private void mergeBooks(Collection<BookBase> list)
    {
    }
    private void mergeBooks_(Collection<BookBase> list)
    {
        if (list != null)
        {
            booksOwned.addAll(list);
        }

    }
    private void mergeCollections(Collection<BookCollection> list)
    {
    }
    private void mergeCollections_(Collection<BookCollection> list)
    {
        if (null != list)
        {
            bookCollections.addAll(list);
        }
    }
    public void update(User other)
    {
        name = other.name == null? name : other.name;
        login = other.login == null? login : other.login;
        email = other.email == null? email : other.email;
        password = other.password == null? password : other.password;
        mergeBooks(other.booksOwned);
        mergeCollections(other.bookCollections);
    }

    public Collection<BookBase> getBooksOwned()
    {
        return booksOwned;
    }

    public void addBook(BookBase book)
    {
        booksOwned.add(book);
    }

    @Override
    public String toString()
    {
        return login + " <" + name + ": " + email + ">";
    }

}
