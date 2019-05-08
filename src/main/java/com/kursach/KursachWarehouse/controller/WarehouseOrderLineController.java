package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.WarehouseOrderLine;
import com.kursach.KursachWarehouse.repos.WarehouseOrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WarehouseOrderLineController {
    @Autowired
    private WarehouseOrderLineRepository WarehouseOrderLineRepo;

    @GetMapping("/warehouseOrderLine")
    public String warehouseOrderLine(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<WarehouseOrderLine> warehouseOrderLines;

        if (filter != null && !filter.isEmpty()) {
            warehouseOrderLines = WarehouseOrderLineRepo.findWarehouseOrderLineByWarehouseOrder_Id(Long.parseLong(filter));
        } else {
            warehouseOrderLines = WarehouseOrderLineRepo.findAll();
        }
        model.addAttribute("warehouseOrderLines", warehouseOrderLines);
        model.addAttribute("filter", filter);
        return "warehouseOrderLine";
    }
}
