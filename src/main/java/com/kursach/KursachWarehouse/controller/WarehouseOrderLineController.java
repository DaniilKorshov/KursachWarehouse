package com.kursach.KursachWarehouse.controller;

import com.kursach.KursachWarehouse.domain.*;
import com.kursach.KursachWarehouse.domain.enums.CellStatus;
import com.kursach.KursachWarehouse.domain.enums.TaskStatus;
import com.kursach.KursachWarehouse.domain.enums.WarehouseOrderType;
import com.kursach.KursachWarehouse.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
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

    @Autowired
    private InventSumRepository InventSumRepo;

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
    public String addWarehouseOrderLine(WarehouseOrderLine orderLine,Map<String,Object> model)
    {

        Set<WarehouseOrderType> orderTypeS=new HashSet<>();
        orderTypeS.add(WarehouseOrderType.SHIPMENT);

        Set<WarehouseOrderType> orderTypeR=new HashSet<>();
        orderTypeR.add(WarehouseOrderType.RECEIPT);

        Set<WarehouseOrderType> orderTypeM=new HashSet<>();
        orderTypeM.add(WarehouseOrderType.MOVING);

        Set<CellStatus> cellStatusE=new HashSet<>();
        cellStatusE.add(CellStatus.EMPTY);

        Set<CellStatus> cellStatusT=new HashSet<>();
        cellStatusT.add(CellStatus.TAKEN);

        Set<CellStatus> cellStatusR=new HashSet<>();
        cellStatusR.add(CellStatus.RESERVED);

        if (WarehouseOrderRepo.findById(orderLine.getWarehouseOrder().getId()).isEmpty()||
        InventRepo.findById(orderLine.getInvent().getId())==null||UserRepo.findById(orderLine.getUser().getId())==null||
                CellRepo.findById(orderLine.getStartLocation().getId())==null||
                        CellRepo.findById(orderLine.getFinishLocation().getId())==null)
        {
            model.put("tableName","Линии Складских заказов");
            model.put("action","/addWarehouseOrderLine");
            model.put("crudName","Добавить");
            model.put("StatusY", TaskStatus.YES);
            model.put("StatusN",TaskStatus.NO);


            if (InventRepo.findById(orderLine.getInvent().getId())==null) {
                model.put("message", "Введенного груза не существует");
            }
            if (WarehouseOrderRepo.findById(orderLine.getWarehouseOrder().getId())==null) {
                model.put("message", "Введенного складского заказа не существует");
            }
            if (UserRepo.findById(orderLine.getUser().getId())==null) {
                model.put("message", "Выбранного пользователя не существует");
            }
            if ( CellRepo.findById(orderLine.getStartLocation().getId())==null) {
                model.put("message", "Стартовой ячейки не существует");
            }
            if (CellRepo.findById(orderLine.getFinishLocation().getId())==null) {
                model.put("message", "Финишной ячейки не существует");
            }
            return "warehouseOrderLineChange";
        }
        System.out.println(WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeS));

        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeS)) { //для shipment
            System.out.println("SHIPMent");

                if (CellRepo.findById(orderLine.getStartLocation().getId()).getCellStatus()==cellStatusE||
                        CellRepo.findById(orderLine.getStartLocation().getId()).getCellStatus()==cellStatusR)
                {
                    model.put("tableName","Линии Складских заказов");
                    model.put("action","/addWarehouseOrderLine");
                    model.put("crudName","Добавить");
                    model.put("StatusY", TaskStatus.YES);
                    model.put("StatusN",TaskStatus.NO);
                    model.put("message","Стартовая ячейка пуста");
                    return "warehouseOrderLineChange";
                }
                else
                {
                    Cell cell=CellRepo.findById(orderLine.getStartLocation().getId());
                    cell.setCellStatus(cellStatusR);
                    CellRepo.save(cell);
                    WarehouseOrderLineRepo.save(orderLine);
                    return "redirect:/warehouseOrderLine";
                }
        }
        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeR))  {
            Cell cell123=CellRepo.findById(orderLine.getFinishLocation().getId());
            System.out.println("RECEIPT"+cell123.getCellStatus()+cellStatusR);
                if (CellRepo.findById(orderLine.getFinishLocation().getId()).getCellStatus().equals(cellStatusR)||
                        CellRepo.findById(orderLine.getFinishLocation().getId()).getCellStatus().equals(cellStatusT))
                {
                    model.put("tableName","Линии Складских заказов");
                    model.put("action","/addWarehouseOrderLine");
                    model.put("crudName","Добавить");
                    model.put("StatusY", TaskStatus.YES);
                    model.put("StatusN",TaskStatus.NO);
                    model.put("message","Финишная ячейка занята");
                    return "warehouseOrderLineChange";
                }
                else
                {
                    Cell cell=CellRepo.findById(orderLine.getFinishLocation().getId());
                    cell.setCellStatus(cellStatusR);
                    CellRepo.save(cell);
                    WarehouseOrderLineRepo.save(orderLine);
                    return "redirect:/warehouseOrderLine";
                }
        }
        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeM))  { //для moving

            if (CellRepo.findById(orderLine.getStartLocation().getId()).getCellStatus()==cellStatusE||
                    CellRepo.findById(orderLine.getStartLocation().getId()).getCellStatus()==cellStatusR)
            {
                model.put("tableName","Линии Складских заказов");
                model.put("action","/addWarehouseOrderLine");
                model.put("crudName","Добавить");
                model.put("StatusY", TaskStatus.YES);
                model.put("StatusN",TaskStatus.NO);
                model.put("message","Стартовая ячейка пуста");
                return "warehouseOrderLineChange";
            }
            if (CellRepo.findById(orderLine.getFinishLocation().getId()).getCellStatus().equals(cellStatusR)||
                    CellRepo.findById(orderLine.getFinishLocation().getId()).getCellStatus().equals(cellStatusT))
            {
                model.put("tableName","Линии Складских заказов");
                model.put("action","/addWarehouseOrderLine");
                model.put("crudName","Добавить");
                model.put("StatusY", TaskStatus.YES);
                model.put("StatusN",TaskStatus.NO);
                model.put("message","Финишная ячейка занята");
                return "warehouseOrderLineChange";
            }
            Cell cell=CellRepo.findById(orderLine.getStartLocation().getId());
            cell.setCellStatus(cellStatusR);
            CellRepo.save(cell);

            Cell cell1=CellRepo.findById(orderLine.getFinishLocation().getId());
            cell1.setCellStatus(cellStatusR);
            CellRepo.save(cell1);

            System.out.println("Moving");
            WarehouseOrderLineRepo.save(orderLine);
            return "redirect:/warehouseOrderLine";
        }
        model.put("tableName","Линии Складских заказов");
        model.put("action","/addWarehouseOrderLine");
        model.put("crudName","Добавить");
        model.put("StatusY", TaskStatus.YES);
        model.put("StatusN",TaskStatus.NO);
        model.put("message", "Проверьте введенные данные    ");
        return "warehouseOrderLineChange";
    }
    @GetMapping("/changeWarehouseOrderLine")
    public String changeWarehouseOrderLine(@RequestParam (name="id",required = false,defaultValue = "0") Long ID,Map<String,Object> model,Model model2){
        WarehouseOrderLine warehouseOrderLine=WarehouseOrderLineRepo.findById(ID);
        model2.addAttribute("ThisWOL",warehouseOrderLine);
        model.put("tableName","Линии Складских заказов");
        model.put("action","/changeWarehouseOrderLine");
        model.put("message", "Введите данные");
        model.put("crudName","Изменить");
        model.put("StatusY", TaskStatus.YES);
        model.put("StatusN",TaskStatus.NO);
        return "warehouseOrderLineChangeInfo";
    }

    @Transactional
    @PostMapping("/changeWarehouseOrderLine")
    public String changeWarehouseOrderLine(WarehouseOrderLine orderLine,Map<String,Object> model,Model model2)
    {
        Cell cell=new Cell();
        Set<TaskStatus> taskStatusY=new HashSet<>();
        taskStatusY.add(TaskStatus.YES);

        Set<TaskStatus> taskStatusN=new HashSet<>();
        taskStatusN.add(TaskStatus.NO);

        Set<WarehouseOrderType> orderTypeS=new HashSet<>();
        orderTypeS.add(WarehouseOrderType.SHIPMENT);

        Set<WarehouseOrderType> orderTypeR=new HashSet<>();
        orderTypeR.add(WarehouseOrderType.RECEIPT);

        Set<WarehouseOrderType> orderTypeM=new HashSet<>();
        orderTypeM.add(WarehouseOrderType.MOVING);

        Set<CellStatus> cellStatusE=new HashSet<>();
        cellStatusE.add(CellStatus.EMPTY);

        Set<CellStatus> cellStatusT=new HashSet<>();
        cellStatusT.add(CellStatus.TAKEN);

        Set<CellStatus> cellStatusR=new HashSet<>();
        cellStatusR.add(CellStatus.RESERVED);

        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeS)){
            if (orderLine.getTakeStatus().equals(taskStatusY)) {
                cell = CellRepo.findById(orderLine.getStartLocation().getId());
                cell.setCellStatus(cellStatusE);
                CellRepo.save(cell);
                InventSumRepo.deleteById(InventSumRepo.findByCell_Id(cell.getId()).getId());
            }
        }
        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeR)){
            if (orderLine.getPutStatus().equals(taskStatusY)){
                cell = CellRepo.findById(orderLine.getFinishLocation().getId());
                cell.setCellStatus(cellStatusT);
                CellRepo.save(cell);

                InventSum inventSum=new InventSum();
                inventSum.setCell(orderLine.getFinishLocation());
                inventSum.setInvent(orderLine.getInvent());
                inventSum.setQty(orderLine.getQty());
                InventSumRepo.save(inventSum);
            }
        }
        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeM)){}

        WarehouseOrderLineRepo.save(orderLine);
        return "redirect:/warehouseOrderLine";
    }
    @GetMapping("/currentUserWarehouseOrderLine")
    @PreAuthorize("hasAuthority('USER')")
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
