package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.*;
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
    private WarehouseRepository WarehouseRepo ;

    @Autowired
    private CellRepository CellRepo;

    @Autowired
    private WarehouseOrderRepository WarehouseOrderRepo;

    @Autowired
    private WarehouseOrderLineRepository WarehouseOrderLineRepo;

    @Autowired
    private DimGroupRepository DimGroupRepo;

    @Autowired
    private InventRepository InventRepo;




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

    @GetMapping("/warehouseOrderLine")
    public String warehouseOrderLine(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<WarehouseOrderLine> warehouseOrderLines= WarehouseOrderLineRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            warehouseOrderLines= WarehouseOrderLineRepo.findByWarehouseOrder(Long.parseLong(filter));
        }
        else {
            warehouseOrderLines= WarehouseOrderLineRepo.findAll();
        }
        model.addAttribute("warehouseOrderLines", warehouseOrderLines);
        model.addAttribute("filter",filter);
        return "warehouseOrderLine";
    }

    @GetMapping("/dimGroup")
    public String dimGroup(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Dimgroup> dimGroups= DimGroupRepo.findAll();

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
    @GetMapping("/invent")
    public String invent(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Invent> invents= InventRepo.findAll();

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

    @GetMapping("/choose_table")
    public String choose_table(Map<String, Object> model){
        return "chooseTable";
    }


}