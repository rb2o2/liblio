package ru.pangaia.example.bookstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Entity
//@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class BookBase extends BaseEntity implements Serializable, Comparable<BookBase>
{
    private static final long serialVersionUID = 1L;

    private String author;
    private String title;
//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<Category> categories = new HashSet<>();

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
        setDateModified(Timestamp.from(Instant.now()));
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
        setDateModified(Timestamp.from(Instant.now()));
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
        setDateModified(Timestamp.from(Instant.now()));
    }

//    public Set<Category> getCategories()
//    {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories)
//    {
//        this.categories = categories;
//        setDateModified(Timestamp.from(Instant.now()));
//    }

//    public void addCategory(Category category)
//    {
//        this.categories.add(category);
//        setDateModified(Timestamp.from(Instant.now()));
//    }
//
//    public void addAllCategories(Collection<Category> categories)
//    {
//        this.categories.addAll(categories);
//        setDateModified(Timestamp.from(Instant.now()));
//    }

    @Override
    public String toString()
    {
        return "[book] id: " + getId() + "; author: " + author + "; title: " + title;
    }

    @Override
    public int compareTo(BookBase bookBase)
    {
        return author.compareTo(bookBase.author) == 0 ? title.compareTo(bookBase.title) : author.compareTo(bookBase.author);
    }
}
