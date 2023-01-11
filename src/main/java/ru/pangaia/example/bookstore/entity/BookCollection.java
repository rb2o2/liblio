package ru.pangaia.example.bookstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class BookCollection extends BaseEntity implements Serializable, Comparable<BookCollection> {
    public BookCollection() {}

    public BookCollection(String name) {
        this.name = name;
    }

    public BookCollection(Collection<BookBase> books) {
        this.books.addAll(books);
    }

    private static final long serialVersionUID = 1L;

    public String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Collection<BookBase> books = new HashSet<>();

    public void addBook(BookBase book) {
        books.add(book);
    }

    public void addAll(Collection<BookBase> books) {
        this.books.addAll(books);
    }

    public void removeBook(BookBase book) {
        books.remove(book);
    }

    public void clear() {
        books.clear();
    }

    @Override
    public int compareTo(BookCollection bookCollection) {
        return name.compareTo(bookCollection.name);
    }
}
