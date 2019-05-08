package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Invent;
import com.kursach.KursachWarehouse.repos.InventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InventController {
    @Autowired
    private InventRepository InventRepo;

    @GetMapping("/invent")
    public String invent(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Invent> invents;

        if (filter!=null&&!filter.isEmpty()){
            invents = InventRepo.findByNameContaining(filter);
        }
        else {
            invents= InventRepo.findAll();
        }
        model.addAttribute("invents", invents);
        model.addAttribute("filter",filter);
        return "inventTable";
    }
}
