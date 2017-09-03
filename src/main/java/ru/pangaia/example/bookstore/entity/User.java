package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;


    public String name;

    public String login;

    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<BookBase> booksOwned = new ArrayList<>();
    @OneToMany
    public List<BookCollection> bookCollections = new ArrayList<>();

    public User(String username)
    {
        this.name = username;
        this.login = String.join("_", Arrays.asList(username.split(" "))).toLowerCase();
        this.password = "admin";
        this.booksOwned = new ArrayList<>();
        this.bookCollections = new ArrayList<>();
    }
    public User()
    {}

    public List<BookBase> getBooksOwned()
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
        return login + "<" + name + ">";
    }

}
