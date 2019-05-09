package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.WarehouseOrderLine;
import com.kursach.KursachWarehouse.repos.WarehouseOrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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

    @Transactional
    @GetMapping("/delWarehouseOrderLine")
    public String delWarehouseOrderLine(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
    {
        WarehouseOrderLineRepo.deleteById(ID);
        return "redirect:/warehouseOrderLine";
    }

    @GetMapping("/addWarehouseOrderLine")
    public String addWarehouseOrderLine(Map<String,Object> model){
        model.put("tableName","Линии Складских заказов");
        model.put("message","Впишите данные");
        model.put("action","/addWarehouseOrderLine");
        model.put("crudName","Добавить");

        return "warehouseOrderLineChange";
    }

    @PostMapping("/addWarehouseOrderLine")
    public String addWarehouseOrder(WarehouseOrderLine orderLine,Map<String,Object> model)
    {

        WarehouseOrderLineRepo.save(orderLine);
        return "redirect:/warehouseOrderLine";
    }
}
