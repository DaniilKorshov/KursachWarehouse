package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Invent;
import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.domain.WarehouseOrder;
import com.kursach.KursachWarehouse.domain.WarehouseOrderLine;
import com.kursach.KursachWarehouse.domain.enums.TaskStatus;
import com.kursach.KursachWarehouse.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private WarehouseOrderRepository WarehouseOrderRepo;

    @Autowired
    private InventRepository InventRepo;

    @Autowired
    private CellRepository CellRepo;

    @Autowired
    private UserRepository UserRepo;

    @Autowired
    private UserRepo UserRepoSecur;

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
        model.put("StatusY", TaskStatus.YES);
        model.put("StatusN",TaskStatus.NO);
        return "warehouseOrderLineChange";
    }

    @PostMapping("/addWarehouseOrderLine")
    public String addWarehouseOrder(WarehouseOrderLine orderLine,Map<String,Object> model)
    {
        if (WarehouseOrderRepo.findById(orderLine.getWarehouseOrder().getId()).isEmpty()||
        InventRepo.findById(orderLine.getInvent().getId())==null||CellRepo.findById(orderLine.getStartLocation().getId())==null||
                CellRepo.findById(orderLine.getFinishLocation().getId())==null||UserRepo.findById(orderLine.getUser().getId())==null)
        {
            model.put("tableName","Линии Складских заказов");
            model.put("action","/addWarehouseOrderLine");
            model.put("message","Впишите данные");
            model.put("crudName","Добавить");
            model.put("StatusY", TaskStatus.YES);
            model.put("StatusN",TaskStatus.NO);

            System.out.println(orderLine.getInvent().getId().toString()+orderLine.getWarehouseOrder().getId().toString()+
                    orderLine.getStartLocation().getId().toString()+orderLine.getFinishLocation().getId().toString()+orderLine.getUser().getId().toString());
            if (InventRepo.findById(orderLine.getInvent().getId())==null) {
                model.put("message", "Введенного груза не существует");
            }
            if (WarehouseOrderRepo.findById(orderLine.getWarehouseOrder().getId())==null) {
                model.put("message", "Введенного складского заказа не существует");
            }
            if (CellRepo.findById(orderLine.getStartLocation().getId())==null) {
                model.put("message", "Стартовой ячейки не существует");
            }
            if (CellRepo.findById(orderLine.getFinishLocation().getId())==null) {
                model.put("message", "Финишной ячейки не существует");
            }
            if (UserRepo.findById(orderLine.getUser().getId())==null) {
                model.put("message", "Выбранного пользователя не существует");
            }
            return "warehouseOrderLineChange";
        }
        WarehouseOrderLineRepo.save(orderLine);
        return "redirect:/warehouseOrderLine";
    }
    @GetMapping("/currentUserWarehouseOrderLine")
    public String currentUserWarehouseOrderLine(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<WarehouseOrderLine> warehouseOrderLines;
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = UserRepoSecur.findByEmail(userDetails.getUsername());
        if (filter != null && !filter.isEmpty()) {
            warehouseOrderLines = WarehouseOrderLineRepo.findWarehouseOrderLineByWarehouseOrder_Id(Long.parseLong(filter));
        } else {
            warehouseOrderLines = WarehouseOrderLineRepo.findByUser_Id(user.getId());
        }
        model.addAttribute("warehouseOrderLines", warehouseOrderLines);
        model.addAttribute("filter", filter);
        return "currentUserWarehouseOrderLine";
    }
}
