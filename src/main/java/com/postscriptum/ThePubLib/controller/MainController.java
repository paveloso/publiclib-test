package com.postscriptum.ThePubLib.controller;


import com.postscriptum.ThePubLib.entities.Book;
import com.postscriptum.ThePubLib.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/index")
    public String index(Map<String, Object> model) {

        Iterable<Book> books = bookRepo.findAll();
        model.put("books", books);
        return "index";
    }

    @PostMapping("/index")
    public String add(@RequestParam String title, @RequestParam Integer totalPages, Map<String, Object> model) {

        Book book = new Book(title, totalPages);
        bookRepo.save(book);

        Iterable<Book> books = bookRepo.findAll();
        model.put("books", books);

        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        Iterable<Book> books;

        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByTitle(filter);
        } else {
            books = bookRepo.findAll();
        }
        model.put("books", books);

        return "index";
    }

}
