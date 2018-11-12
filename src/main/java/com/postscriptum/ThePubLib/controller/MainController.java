package com.postscriptum.ThePubLib.controller;


import com.postscriptum.ThePubLib.entities.Book;
import com.postscriptum.ThePubLib.entities.User;
import com.postscriptum.ThePubLib.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        Iterable<Book> books = bookRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByTitle(filter);
        } else {
            books = bookRepo.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "index";
    }

    @PostMapping("/index")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam Integer totalPages, Map<String, Object> model
    ) {

        Book book = new Book(title, totalPages, user);
        bookRepo.save(book);

        Iterable<Book> books = bookRepo.findAll();
        model.put("books", books);

        return "index";
    }

}
