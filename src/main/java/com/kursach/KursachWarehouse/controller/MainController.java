package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepo UserRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = UserRepo.findByEmail(userDetails.getUsername());
        model.put("userNS", user.getName() + " " + user.getSurname());
        model.put("userPhone", user.getPhone_number());
        model.put("userEmail", user.getEmail());
        return "main";
    }

    @GetMapping("/choose_table")
    public String choose_table(Map<String, Object> model) {
        return "chooseTable";
    }


}