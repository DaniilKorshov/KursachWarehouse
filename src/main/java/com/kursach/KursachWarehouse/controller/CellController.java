package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.Cell;
import com.kursach.KursachWarehouse.domain.enums.CellStatus;
import com.kursach.KursachWarehouse.repos.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CellController {
    @Autowired
    private CellRepository CellRepo;

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

    @Transactional
    @GetMapping("/delCell")
    public String delCell(@RequestParam (name="id",required = false,defaultValue = "0") Long ID)
    {
        //проверка на заполненность ячейки
            CellRepo.deleteById(ID);
        return "redirect:/cells";
    }


    @GetMapping("/addCell")
    public String addCell(Map<String,Object> model){
        model.put("tableName","Ячейки");
        model.put("message","Впишите данные");
        model.put("action","/addCell");
        model.put("crudName","Добавить");
        model.put("cellStatusE", CellStatus.EMPTY);
        model.put("cellStatusT",CellStatus.TAKEN);
        model.put("cellStatusR",CellStatus.RESERVED);
        return "cellTableChange";
    }
    @PostMapping("/addCell")
    public String addCell(Cell cell,Map<String,Object> model)
    {
        CellRepo.save(cell);
        return "redirect:/cells";
    }
    @GetMapping("/changeCell")
    public String changeCell(@RequestParam (name="id",required = false,defaultValue = "0") Long ID,Map<String,Object> model,Model model2){
        Cell cell=CellRepo.findById(ID);
        model.put("tableName","Ячейка");
        model.put("message","Впишите данные");
        model.put("action","/changeCell");
        model.put("crudName","изменить");
        model.put("cellStatusE", CellStatus.EMPTY);
        model.put("cellStatusT",CellStatus.TAKEN);
        model.put("cellStatusR",CellStatus.RESERVED);
        model2.addAttribute("ThisCell",cell);
        return "cellTableChangeInfo";
    }
    @PostMapping("/changeCell")
    public String changeCell(Cell cell,Map<String,Object> model,Model model2)
    {
        CellRepo.save(cell);
        return "redirect:/cells";
    }
}
