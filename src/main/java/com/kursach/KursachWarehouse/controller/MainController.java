package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.QRcode.WriteQR;
import com.kursach.KursachWarehouse.domain.Invent;
import com.kursach.KursachWarehouse.domain.InventSum;
import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.domain.enums.UserRole;
import com.kursach.KursachWarehouse.repos.InventRepository;
import com.kursach.KursachWarehouse.repos.InventSumRepository;
import com.kursach.KursachWarehouse.repos.UserRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    private UserRepo UserRepo;

    @Autowired
    private InventRepository InventRepo;

    @Autowired
    private InventSumRepository InventSumRepo;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ADMIN);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = UserRepo.findByEmail(userDetails.getUsername());
        model.put("userNS", user.getName() + " " + user.getSurname());
        model.put("userPhone", user.getPhone_number());
        model.put("userEmail", user.getEmail());
        if (user.getUserRoles().equals(userRoles))
            return "main";
        else
            return "mainUser";
    }

    @GetMapping("/QRcode")
    public String QRcode(Map<String,Object> model) {
        model.put("text","Введите данные");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ADMIN);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = UserRepo.findByEmail(userDetails.getUsername());
        if (user.getUserRoles().equals(userRoles))
            return "QRcode";
        else
            return "QRcodeUser";
    }

    @PostMapping("/QRcode")
    public String QRcode(@RequestParam(name = "id", required = false, defaultValue = "0") Long ID, @RequestParam(name = "qty", required = false, defaultValue = "0") Long qty,Map<String,Object> model) {
        Invent inv=InventRepo.findById(ID);
        if (inv!=null) {
            WriteQR.WriteCode(inv.getId() + " " + inv.getItemType() + " " + inv.getName() + " " + qty + inv.getUnit());
            return "downloadLogFile";
        }
        else {
            model.put("text","Введите ID существующего груза");
            return "QRcode";
        }
    }
    @RequestMapping(value="/downloadLogFile")
    public void getLogFile(HttpSession session, HttpServletResponse response) throws Exception {
        try {
            String filePathToBeServed = "QRcodeGen.png";
                    File fileToDownload = new File(filePathToBeServed);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+"QRcodeGen"+".png");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/inventSum")
    public String inventSum(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<InventSum> inventSums=InventSumRepo.findAll();

        if (filter!=null&&!filter.isEmpty()){
            inventSums=InventSumRepo.findByInvent_Id(Long.parseLong(filter));
        }
        else {
            inventSums=InventSumRepo.findAll();
        }
        model.addAttribute("inventSums", inventSums);
        model.addAttribute("filter",filter);
        return "inventSum";
    }
    @GetMapping("/choose_table")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String choose_table(Map<String, Object> model) {
        return "chooseTable";
    }
}