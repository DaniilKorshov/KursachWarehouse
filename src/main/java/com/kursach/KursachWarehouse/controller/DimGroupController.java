package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Dimgroup;
import com.kursach.KursachWarehouse.domain.Invent;
import com.kursach.KursachWarehouse.repos.DimGroupRepository;
import com.kursach.KursachWarehouse.repos.InventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class DimGroupController {
    @Autowired
    private DimGroupRepository DimGroupRepo;

    @Autowired
    private InventRepository InventRepo;

    @GetMapping("/dimGroup")
    public String dimGroup(@RequestParam(required = false,defaultValue = "") String filter, Model model)
    {
        Iterable<Dimgroup> dimGroups;

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

    @Transactional
    @GetMapping("/delDimGroup")
    public String delDimGroup(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
    {
        Iterable<Invent> inventFind=InventRepo.findByDimgroup_Id(ID);

        if (((List<Invent>) inventFind).isEmpty()==true) {
            DimGroupRepo.deleteById(ID);
        }
        return "redirect:/dimGroup";
    }


    @GetMapping("/addDimGroup")
    public String addDimGroup(Map<String,Object> model){
        model.put("tableName","Комплектации");
        model.put("message","Впишите данные");
        model.put("action","/addDimGroup");
        model.put("crudName","Добавить");
        return "dimGroupTableChange";
    }

    @PostMapping("/addDimGroup")
    public String addDimGroup(Dimgroup dimgroup,Map<String,Object> model)
    {
        Dimgroup dimGroupFind=DimGroupRepo.findByWidthAndLengthAndWeightAndColorAndConfig(dimgroup.getLength(),dimgroup.getWidth(),dimgroup.getWeight(),
                dimgroup.getColor(),dimgroup.getConfig());
        if (dimGroupFind!=null)
        {
            model.put("tableName","Комплектации");
            model.put("message","Такой объект уже существует");
            model.put("action","/addDimGroup");
            model.put("crudName","Добавить");
            return "dimGroupTableChange";
        }
        DimGroupRepo.save(dimgroup);
        return "redirect:/dimGroup";
    }


}
