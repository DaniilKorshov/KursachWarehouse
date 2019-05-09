package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.domain.WarehouseOrderLine;
import com.kursach.KursachWarehouse.repos.UserRepository;
import com.kursach.KursachWarehouse.repos.WarehouseOrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private WarehouseOrderLineRepository WarehouseOrderLineRepo;

    @GetMapping("/users")
    public String users(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<User> users;

        if (filter!=null&&!filter.isEmpty()){
            users= UserRepository.findBySurnameContaining(filter);
        }
        else {
            users= UserRepository.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter",filter);
        return "userTable";
    }
//    @Transactional
//    @GetMapping("/delDimGroup")
//    public String delDimGroup(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
//    {
//       // Iterable<WarehouseOrderLine> OrderLineFind=WarehouseOrderLineRepo.find(ID);
//
//        if (((List<Invent>) inventFind).isEmpty()==true) {
//            DimGroupRepo.deleteById(ID);
//        }
//        return "redirect:/dimGroup";
//    }

}
