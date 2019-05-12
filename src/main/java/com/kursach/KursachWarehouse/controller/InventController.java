package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Dimgroup;
import com.kursach.KursachWarehouse.domain.Invent;
import com.kursach.KursachWarehouse.domain.enums.ItemType;
import com.kursach.KursachWarehouse.repos.DimGroupRepository;
import com.kursach.KursachWarehouse.repos.InventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class InventController {
    @Autowired
    private InventRepository InventRepo;

    @Autowired
    private DimGroupRepository DimGroupRepo;

    @GetMapping("/invent")
    public String invent(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Invent> invents;

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

    @Transactional
    @GetMapping("/delInvent")
    public String delInvent(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
    {
        InventRepo.deleteById(ID);
        return "redirect:/invent";
    }

    @GetMapping("/addInvent")
    public String addInvent(Map<String,Object> model){
        model.put("tableName","Грузы");
        model.put("message","Впишите данные");
        model.put("action","/addInvent");
        model.put("crudName","Добавить");
        model.put("InventTypeM", ItemType.MATERIAL);
        model.put("InventTypeP", ItemType.PRODUCT);
        return "inventTableChange";
    }

    @PostMapping("/addInvent")
    public String addInvent(Invent invent,Map<String,Object> model)
    {
        Invent inventFind=InventRepo.findByNameAndDimgroup_Id(invent.getName(),invent.getDimgroup().getId());
        Iterable<Dimgroup> dimgroupFind=DimGroupRepo.findById(invent.getDimgroup().getId());
        if (((List<Dimgroup>) dimgroupFind).isEmpty()==true)
        {
            model.put("tableName","Грузы");
            model.put("message","Не существует такой комплектации,введите данные повторно");
            model.put("action","/invent");
            model.put("crudName","Добавить");
            model.put("InventTypeM", ItemType.MATERIAL);
            model.put("InventTypeP", ItemType.PRODUCT);
            return "inventTableChange";
        }
        if (inventFind!=null)
        {
            model.put("tableName","Грузы");
            model.put("message","Такой объект уже существует");
            model.put("action","/invent");
            model.put("crudName","Добавить");
            model.put("InventTypeM", ItemType.MATERIAL);
            model.put("InventTypeP", ItemType.PRODUCT);
            return "inventTableChange";
        }
        InventRepo.save(invent);
        return "redirect:/invent";
    }
}
