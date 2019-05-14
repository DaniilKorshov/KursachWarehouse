package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.domain.enums.UserRole;
import com.kursach.KursachWarehouse.repos.UserRepository;
import com.kursach.KursachWarehouse.repos.WarehouseOrderLineRepository;
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
public class UserController {

    @Autowired
    private UserRepository UserRepo;

    @Autowired
    private WarehouseOrderLineRepository WarehouseOrderLineRepo;

    @GetMapping("/users")
    public String users(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<User> users;

        if (filter!=null&&!filter.isEmpty()){
            users= UserRepo.findBySurnameContaining(filter);
        }
        else {
            users= UserRepo.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter",filter);
        return "userTable";
    }

    @Transactional
    @GetMapping("/delUser")
    public String delUser(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
    {
        //проверка на заполненность ячейки
        if(WarehouseOrderLineRepo.findByUser_Id(ID).isEmpty()) {
            UserRepo.deleteById(ID);
        }
        return "redirect:/users";
    }

    @GetMapping("/addUser")
    public String addUser(Map<String,Object> model){
        model.put("tableName","Пользователи");
        model.put("message","Впишите данные");
        model.put("action","/addUser");
        model.put("crudName","Добавить");
        model.put("userRoleU", UserRole.USER);
        model.put("userRoleA", UserRole.ADMIN);
        return "userTableChange";
    }
    @PostMapping("/addUser")
    public String addUser(User user,Map<String,Object> model)
    {
        if (UserRepo.findByEmail(user.getEmail())!=null)
        {
            model.put("tableName","Пользователи");
            model.put("message","Такой пользователй уже существует");
            model.put("action","/addUser");
            model.put("crudName","Добавить");
            model.put("userRoleU", UserRole.USER);
            model.put("userRoleA", UserRole.ADMIN);
            return "userTableChange";
        }
        UserRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/changeUser")
    public String changeUser(@RequestParam (name="id",required = false,defaultValue = "0") Long ID,Map<String,Object> model,Model model2){
        User user=UserRepo.findById(ID);
        model.put("tableName","Пользователь");
        model.put("message","Впишите данные");
        model.put("action","/changeUser");
        model.put("crudName","Изменить");
        model.put("userRoleU", UserRole.USER);
        model.put("userRoleA", UserRole.ADMIN);
        model2.addAttribute("ThisUser",user);
        return "userTableChangeInfo";
    }
    @PostMapping("/changeUser")
    public String changeUser(User user,Map<String,Object> model,Model model2)
    {
        UserRepo.save(user);
        return "redirect:/users";
    }
}
