package com.thanhtuyen.webbook.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "cover", columnDefinition = "MEDIUMBLOB")
    private String cover;
    private String description;
    private String isbn;
    private String language;
    private int page;
    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean is_active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "catergory_id"))
    private Set<Category> categories;

    public Book(String title, String cover, String description, String isbn, String language, int page, boolean is_active, Author author, Set<Category> categories) {
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.isbn = isbn;
        this.language = language;
        this.page = page;
        this.is_active = is_active;
        this.author = author;
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cover=" + cover +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", language='" + language + '\'' +
                ", page=" + page +
                ", is_active=" + is_active +
                ", author=" + author +
                '}';
    }
}
