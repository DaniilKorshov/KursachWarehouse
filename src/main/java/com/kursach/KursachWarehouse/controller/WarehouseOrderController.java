package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.WarehouseOrder;
import com.kursach.KursachWarehouse.repos.WarehouseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WarehouseOrderController {
    @Autowired
    private WarehouseOrderRepository WarehouseOrderRepo;

    @GetMapping("/warehouseOrders")
    public String warehouseOrders(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<WarehouseOrder> warehouseOrders;

        if (filter != null && !filter.isEmpty()) {
            warehouseOrders = WarehouseOrderRepo.findById(Long.parseLong(filter));
        } else {
            warehouseOrders = WarehouseOrderRepo.findAll();
        }
        model.addAttribute("warehouseOrders", warehouseOrders);
        model.addAttribute("filter", filter);
        return "warehouseOrder";
    }
}
