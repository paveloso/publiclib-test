package com.postscriptum.ThePubLib.repos;

import com.postscriptum.ThePubLib.entities.BkAuthor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepo extends CrudRepository<BkAuthor, Long> {

    List<BkAuthor> findByBkAuthor(String authorsName);
}
