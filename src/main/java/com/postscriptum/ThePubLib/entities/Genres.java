package com.postscriptum.ThePubLib.entities;

import javax.persistence.*;

@Entity
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String genreType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book BkGenre;

    public Genres(String genreType, Book bkGenre) {
        this.genreType = genreType;
        BkGenre = bkGenre;
    }

    public Genres() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreType() {
        return genreType;
    }

    public void setGenreType(String genreType) {
        this.genreType = genreType;
    }

    public Book getBkGenre() {
        return BkGenre;
    }

    public void setBkGenre(Book bkGenre) {
        BkGenre = bkGenre;
    }
}
