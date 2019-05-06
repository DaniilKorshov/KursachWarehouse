package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.Principal;
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
    public String main(Map<String, Object> model, Principal principal){
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("userNS",userDetails.getUsername());
        return "main";
    }

    @GetMapping("/users")
    public String users(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<User> users=UserRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            users=UserRepo.findBySurname(filter);
        }
        else {
            users=UserRepo.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter",filter);
        return "table_page";
    }


    @GetMapping("/choose_table")
    public String choose_table(Map<String, Object> model){
        return "chooseTable";
    }


}