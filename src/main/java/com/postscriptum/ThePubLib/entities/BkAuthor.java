package com.postscriptum.ThePubLib.entities;

import javax.persistence.*;

@Entity
public class BkAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authorName;

    private String afilename;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book writtenBook;

    public BkAuthor() {
    }

    public BkAuthor(String authorName, String afilename, Book writtenBook) {
        this.authorName = authorName;
        this.afilename = afilename;
        this.writtenBook = writtenBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAfilename() {
        return afilename;
    }

    public void setAfilename(String afilename) {
        this.afilename = afilename;
    }

    public Book getWrittenBook() {
        return writtenBook;
    }

    public void setWrittenBook(Book writtenBook) {
        this.writtenBook = writtenBook;
    }
}
