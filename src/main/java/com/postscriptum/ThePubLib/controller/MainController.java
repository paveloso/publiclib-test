package com.postscriptum.ThePubLib.controller;


import com.postscriptum.ThePubLib.entities.Book;
import com.postscriptum.ThePubLib.entities.User;
import com.postscriptum.ThePubLib.repos.BookRepo;
import com.postscriptum.ThePubLib.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private BookRepo bookRepo;

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam Integer totalPages, Map<String, Object> model,
            @RequestParam("file")MultipartFile file
            ) throws IOException {

        Book book = new Book(title, totalPages, user);

        saveImageFile(file, book);

        bookRepo.save(book);

        Iterable<Book> books = bookRepo.findAll();
        model.put("books", books);

        return "index";
    }

    private void saveImageFile(@RequestParam("file") MultipartFile file, Book book) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));


            book.setBfilename(resultFilename);
        }
    }

    @GetMapping("/user-books/{user}")
    public String userBooks(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Book book
    ) {

        Set<Book> books = user.getBooks();

        model.addAttribute("books", books);
        model.addAttribute("book", book);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userBooks";
    }

    @PostMapping("/user-books/{user}")
    public String updateBook(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Book book,
            @RequestParam("title") String title,
            @RequestParam("totalPages") Integer totalPages,
            @RequestParam("file")MultipartFile file
    ) throws IOException {
        if (book.getAddedByUser().equals(currentUser)) {
            if (!StringUtils.isEmpty(title)) {
                book.setTitle(title);
            }

            if (!StringUtils.isEmpty(String.valueOf(totalPages))) {
                book.setTotalPages(totalPages);
            }

            saveImageFile(file, book);

            bookRepo.save(book);
        }

        return "redirect:/user-books/" + user;
    }

}
