package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Warehouse;
import com.kursach.KursachWarehouse.repos.CellRepository;
import com.kursach.KursachWarehouse.repos.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class WarehouseController {
    @Autowired
    private WarehouseRepository WarehouseRepo;

    @Autowired
    private CellRepository CellRepo;

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

    @Transactional
    @GetMapping("/delWarehouse")
    public String delWarehouse(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
    {
        if (CellRepo.findByWarehouse_Id(ID).isEmpty()==true) {
            WarehouseRepo.deleteById(ID);
        }
        return "redirect:/warehouse";
    }

    @GetMapping("/addWarehouse")
    public String addWarehouse(Map<String,Object> model){
        model.put("tableName","Складские помещения");
        model.put("message","Впишите данные");
        model.put("action","/addWarehouse");
        model.put("crudName","Добавить");
        return "warehouseTableChange";
    }
    @PostMapping("/addWarehouse")
    public String addWarehouse(Warehouse warehouse,Map<String,Object> model)
    {
        WarehouseRepo.save(warehouse);
        return "redirect:/warehouse";
    }
}
