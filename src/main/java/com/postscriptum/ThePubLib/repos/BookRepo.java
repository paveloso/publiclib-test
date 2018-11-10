package com.postscriptum.ThePubLib.repos;

import com.postscriptum.ThePubLib.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Integer> {

    List<Book> findByTitle(String title);
}
