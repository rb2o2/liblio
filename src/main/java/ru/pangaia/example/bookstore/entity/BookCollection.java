package ru.pangaia.example.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class BookCollection implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    private List<BookBase> books;

    public void addBook(BookBase book)
    {
        books.add(book);
    }
    public void removeBook(BookBase book)
    {
        books.remove(book);
    }
    public void clear()
    {
        books.clear();
    }

    public BookCollection(Collection<BookBase> books)
    {
        books.addAll(books);
    }
}
