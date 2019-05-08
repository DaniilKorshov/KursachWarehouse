package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Warehouse;
import com.kursach.KursachWarehouse.repos.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WarehouseController {
    @Autowired
    private WarehouseRepository WarehouseRepo;

    @GetMapping("/warehouse")
    public String warehouse(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Warehouse> warehouses;

        if (filter != null && !filter.isEmpty()) {
            warehouses = WarehouseRepo.findByWarehouseAddressContaining(filter);
        } else {
            warehouses = WarehouseRepo.findAll();
        }
        model.addAttribute("warehouses", warehouses);
        model.addAttribute("filter", filter);
        return "warehouseTable";
    }
}
