package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.enums.UserRole;
import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepo.findByEmail(user.getEmail());
        List<User> checkAdmin=userRepo.findByUserRoles(UserRole.ADMIN);
        if (userFromDb!=null){
            model.put("message","Такой пользователь уже существует");
            return "registration";
        }
        user.setActive(true);
        if (checkAdmin.isEmpty()==true)
            user.setUserRoles(Collections.singleton(UserRole.ADMIN));
        else
            user.setUserRoles(Collections.singleton(UserRole.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
