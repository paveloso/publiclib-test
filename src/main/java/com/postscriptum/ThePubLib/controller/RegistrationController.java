package com.postscriptum.ThePubLib.controller;

import com.postscriptum.ThePubLib.entities.Role;
import com.postscriptum.ThePubLib.entities.User;
import com.postscriptum.ThePubLib.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        //model.put("message", "LOG DEBUG");

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        } else {
            model.put("message", "");
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
