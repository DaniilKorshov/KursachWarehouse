package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Cell;
import com.kursach.KursachWarehouse.repos.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CellController {
    @Autowired
    private CellRepository CellRepo;

    @GetMapping("/cells")
    public String cells(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Cell> cells=CellRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            cells=CellRepo.findByWeightGreaterThanEqual(Double.parseDouble(filter));
        }
        else {
            cells=CellRepo.findAll();
        }
        model.addAttribute("cells", cells);
        model.addAttribute("filter",filter);
        return "cellTable";
    }
}
