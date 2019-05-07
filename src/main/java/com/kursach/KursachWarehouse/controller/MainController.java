package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Cell;
import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.repos.CellRepository;
import com.kursach.KursachWarehouse.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;



@Controller
public class MainController {
    @Autowired
    private UserRepository UserRepo;

    @Autowired
    private CellRepository CellRepo;



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
        return "userTable";
    }

    @GetMapping("/cells")
    public String cells(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Cell> cells=CellRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            cells=CellRepo.findByWarehouse(filter);
        }
        else {
            cells=CellRepo.findAll();
        }
        model.addAttribute("cells", cells);
        model.addAttribute("filter",filter);
        return "cellTable";
    }



    @GetMapping("/choose_table")
    public String choose_table(Map<String, Object> model){
        return "chooseTable";
    }


}