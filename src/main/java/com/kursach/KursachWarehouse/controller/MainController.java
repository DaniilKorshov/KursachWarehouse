package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserRepository UserRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        return "main";
    }

    @GetMapping("/users")
    public String showUsers(Map<String,Object> model,Map<String,Object> tableName)
    {
        Iterable<User> users=UserRepo.findAll();
        model.put("users", users);
        tableName.put("tableName","User table");
        return "table_page";
    }



}