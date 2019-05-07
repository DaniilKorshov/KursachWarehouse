package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Cell;
import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.domain.Warehouse;
import com.kursach.KursachWarehouse.domain.WarehouseOrder;
import com.kursach.KursachWarehouse.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;



@Controller
public class MainController {
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private UserRepo UserRepo;

    @Autowired
    private CellRepository CellRepo;

    @Autowired
    private WarehouseOrderRepository WarehouseOrderRepo;

    @Autowired
    private WarehouseRepository WarehouseRepo ;



    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user=UserRepo.findByEmail(userDetails.getUsername());
        model.put("userNS",user.getName()+" "+user.getSurname());
        model.put("userPhone",user.getPhone_number());
        model.put("userEmail",user.getEmail());
        return "main";
    }

    @GetMapping("/users")
    public String users(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<User> users= UserRepository.findAll();

        if (filter!=null&&!filter.isEmpty()){
            users= UserRepository.findBySurname(filter);
        }
        else {
            users= UserRepository.findAll();
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
            cells=CellRepo.findByWeightGreaterThanEqual(Double.parseDouble(filter));
        }
        else {
            cells=CellRepo.findAll();
        }
        model.addAttribute("cells", cells);
        model.addAttribute("filter",filter);
        return "cellTable";
    }

    @GetMapping("/warehouseOrders")
    public String warehouseOrders(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<WarehouseOrder> warehouseOrders= WarehouseOrderRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            warehouseOrders= WarehouseOrderRepo.findById(Long.parseLong(filter));
        }
        else {
            warehouseOrders= WarehouseOrderRepo.findAll();
        }
        model.addAttribute("warehouseOrders", warehouseOrders);
        model.addAttribute("filter",filter);
        return "warehouseOrder";
    }

    @GetMapping("/warehouse")
    public String warehouse(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Warehouse> warehouses= WarehouseRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            warehouses= WarehouseRepo.findByWarehouseAddressContaining(filter);
        }
        else {
            warehouses= WarehouseRepo.findAll();
        }
        model.addAttribute("warehouses", warehouses);
        model.addAttribute("filter",filter);
        return "warehouseTable";
    }

    @GetMapping("/choose_table")
    public String choose_table(Map<String, Object> model){
        return "chooseTable";
    }


}