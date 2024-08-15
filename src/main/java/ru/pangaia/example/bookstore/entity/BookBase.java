package ru.pangaia.example.bookstore.entity;

import jakarta.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import lombok.NonNull;

@Entity
public class BookBase extends BaseEntity implements Serializable, Comparable<BookBase> {
    @Serial
    private static final long serialVersionUID = 1L;

    private String author;
    private String title;

    public BookBase(String author, @NonNull String title) {
        this.author = author;
        this.title = title;
    }
    public BookBase() {}

    public void update(BookBase other){
        author = other.author == null? author : other.author;
        title = other.title == null? title: other.title;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    @Override
    public String toString() {
        return "[book] id: " + getId() + "; author: " + author + "; title: " + title;
    }

    @Override
    public int compareTo(BookBase bookBase) {
        return author.compareTo(bookBase.author) == 0 ? title.compareTo(bookBase.title) : author.compareTo(bookBase.author);
    }
}
