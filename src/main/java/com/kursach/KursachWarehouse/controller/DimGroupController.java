package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Dimgroup;
import com.kursach.KursachWarehouse.repos.DimGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DimGroupController {
    @Autowired
    private DimGroupRepository DimGroupRepo;

    @GetMapping("/dimGroup")
    public String dimGroup(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Dimgroup> dimGroups;

        if (filter!=null&&!filter.isEmpty()){
            dimGroups = DimGroupRepo.findById(Long.parseLong(filter));
        }
        else {
            dimGroups= DimGroupRepo.findAll();
        }
        model.addAttribute("dimGroups", dimGroups);
        model.addAttribute("filter",filter);
        return "dimGroupTable";
    }
}
