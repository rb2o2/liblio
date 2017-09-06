package ru.pangaia.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Entity
public class User extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private String login;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<BookBase> booksOwned = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookCollection> bookCollections = new ArrayList<>();

    private static final User DEFAULT = new User("root", "root", "rb2o2.dev@gmail.com","root");
    static User getDefaultUser()
    {
        return DEFAULT;
    }

    public User(String name, String login, String email, String password)
    {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
    }
    public User(String username)
    {
        this.name = username;
        this.login = String.join("_", Arrays.asList(username.split(" "))).toLowerCase();
        this.password = "admin";
    }
    public User()
    {}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        setDateModified(Timestamp.from(Instant.now()));
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
        setDateModified(Timestamp.from(Instant.now()));
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
        setDateModified(Timestamp.from(Instant.now()));
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
        setDateModified(Timestamp.from(Instant.now()));
    }

    private void mergeBooks(Collection<BookBase> list)
    {
    }

    private void mergeBooks_(Collection<BookBase> list)
    {
        if (list != null)
        {
            booksOwned.addAll(list);
            setDateModified(Timestamp.from(Instant.now()));
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
            setDateModified(Timestamp.from(Instant.now()));
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
        setDateModified(Timestamp.from(Instant.now()));
    }

//    public Set<BookBase> getBooksOwned()
//    {
//        return booksOwned;
//    }


    public List<BookCollection> getBookCollections()
    {
        return bookCollections;
    }

    public Set<BookBase> getBooksOwned()
    {
        return booksOwned;
    }

    public void setBooksOwned(Set<BookBase> booksOwned)
    {
        this.booksOwned = booksOwned;
        setDateModified(Timestamp.from(Instant.now()));
    }

//    public List<BookCollection> getBookCollections()
//    {
//        return bookCollections;
//    }

    public void setBookCollections(List<BookCollection> bookCollections)
    {
        this.bookCollections = bookCollections;
        setDateModified(Timestamp.from(Instant.now()));
    }

    public BookCollection getBookCollectionById(Long collId)
    {
        return bookCollections.stream().filter((c) -> collId.equals(c.getId())).findFirst().get();
    }

    public void addBook(BookBase book)
    {
        booksOwned.add(book);
        setDateModified(Timestamp.from(Instant.now()));
    }

    public void addBooks(Collection<BookBase> books)
    {
        booksOwned.addAll(books);
        setDateModified(Timestamp.from(Instant.now()));
    }

    public void addCollection(BookCollection bookCollection)
    {
        bookCollections.add(bookCollection);
        setDateModified(Timestamp.from(Instant.now()));
    }

    public void addCollections(Collection<BookCollection> collections)
    {
        bookCollections.addAll(collections);
        setDateModified(Timestamp.from(Instant.now()));
    }

    @Override
    public String toString()
    {
        return login + " <" + name + ": " + email + ">";
    }

}
