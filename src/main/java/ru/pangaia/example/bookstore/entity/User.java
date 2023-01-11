package ru.pangaia.example.bookstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.*;

@Entity
public class User extends BaseEntity implements Serializable {
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

    public User(String name, String login, String email, String password) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String username) {
        this.name = username;
        this.login = String.join("_", Arrays.asList(username.split(" "))).toLowerCase();
        this.password = "admin";
    }

    public User() {}

    static User getDefaultUser() {
        return DEFAULT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    private void mergeBooks(Collection<BookBase> list) {}

    private void mergeBooks_(Collection<BookBase> list) {
        if (list != null) {
            booksOwned.addAll(list);
        }
    }

    private void mergeCollections(Collection<BookCollection> list) {}

    private void mergeCollections_(Collection<BookCollection> list) {
        if (null != list) {
            bookCollections.addAll(list);
        }
    }

    public void update(User other) {
        name = other.name == null? name : other.name;
        login = other.login == null? login : other.login;
        email = other.email == null? email : other.email;
        password = other.password == null? password : other.password;
        mergeBooks(other.booksOwned);
        mergeCollections(other.bookCollections);
    }

    public List<BookCollection> getBookCollections() {
        return bookCollections;
    }

    public void setBookCollections(List<BookCollection> bookCollections) {
        this.bookCollections = bookCollections;

    }

    public Set<BookBase> getBooksOwned() {
        return booksOwned;
    }

    public void setBooksOwned(Set<BookBase> booksOwned) {
        this.booksOwned = booksOwned;

    }

    public BookCollection getBookCollectionById(Long collId) {
        return bookCollections.stream().filter((c) -> collId.equals(c.getId())).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public void addBook(BookBase book) {
        booksOwned.add(book);
    }

    public void addBooks(Collection<BookBase> books) {
        booksOwned.addAll(books);
    }

    public void addCollection(BookCollection bookCollection) {
        bookCollections.add(bookCollection);
    }

    public void addCollections(Collection<BookCollection> collections) {
        bookCollections.addAll(collections);
    }

    @Override
    public String toString() {
        return login + " <" + name + ": " + email + ">";
    }

}
