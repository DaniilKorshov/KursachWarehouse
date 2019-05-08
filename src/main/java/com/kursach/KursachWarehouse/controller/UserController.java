package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/users")
    public String users(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<User> users;

        if (filter!=null&&!filter.isEmpty()){
            users= UserRepository.findBySurnameContaining(filter);
        }
        else {
            users= UserRepository.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter",filter);
        return "userTable";
    }

}
