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

        if (WarehouseOrderRepo.findById(orderLine.getWarehouseOrder().getId()).isEmpty()||
        InventRepo.findById(orderLine.getInvent().getId())==null||UserRepo.findById(orderLine.getUser().getId())==null)
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
            return "warehouseOrderLineChange";
        }
        System.out.println(WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeS));

        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeS)) { //для shipment
            System.out.println("SHIPMent");
            if (CellRepo.findById(orderLine.getStartLocation().getId()) == null) {
                model.put("tableName","Линии Складских заказов");
                model.put("action","/addWarehouseOrderLine");
                model.put("crudName","Добавить");
                model.put("StatusY", TaskStatus.YES);
                model.put("StatusN",TaskStatus.NO);
                model.put("message", "Стартовой ячейки не существует");
                return "warehouseOrderLineChange";
            }
            else
            {
                if (CellRepo.findById(orderLine.getStartLocation().getId()).getCellStatus().equals(CellStatus.EMPTY)||
                        CellRepo.findById(orderLine.getStartLocation().getId()).getCellStatus().equals(CellStatus.RESERVED))
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
                    WarehouseOrderLineRepo.save(orderLine);
                    return "redirect:/warehouseOrderLine";
                }
            }
        }
        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeR))  {
            System.out.println("RECEIPT");
            if (CellRepo.findById(orderLine.getFinishLocation().getId()) == null) {
                model.put("tableName","Линии Складских заказов");
                model.put("action","/addWarehouseOrderLine");
                model.put("crudName","Добавить");
                model.put("StatusY", TaskStatus.YES);
                model.put("StatusN",TaskStatus.NO);
                model.put("message", "Финишной ячейки не существует");
                return "warehouseOrderLineChange";
            }
            else
            {
                if (!CellRepo.findById(orderLine.getFinishLocation().getId()).getCellStatus().equals(CellStatus.EMPTY))
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
                    Set<CellStatus> statuses=new HashSet<>();
                    statuses.add(CellStatus.RESERVED);
                    Cell cell=CellRepo.findById(orderLine.getFinishLocation().getId());
                    cell.setCellStatus(statuses);
                    CellRepo.save(cell);
                    WarehouseOrderLineRepo.save(orderLine);
                    return "redirect:/warehouseOrderLine";
                }
            }
        }
        if (WarehouseOrderRepo.findAllById(orderLine.getWarehouseOrder().getId()).getType().equals(orderTypeM))  { //для moving
            System.out.println("Moving");
            if (CellRepo.findById(orderLine.getStartLocation().getId()) == null) {
                model.put("tableName","Линии Складских заказов");
                model.put("action","/addWarehouseOrderLine");
                model.put("crudName","Добавить");
                model.put("StatusY", TaskStatus.YES);
                model.put("StatusN",TaskStatus.NO);
                model.put("message", "Стартовой ячейки не существует");
                return "warehouseOrderLineChange";
            }
            if (CellRepo.findById(orderLine.getFinishLocation().getId()) == null) {
                model.put("tableName","Линии Складских заказов");
                model.put("action","/addWarehouseOrderLine");
                model.put("crudName","Добавить");
                model.put("StatusY", TaskStatus.YES);
                model.put("StatusN",TaskStatus.NO);
                model.put("message", "Финишной ячейки не существует");
                return "warehouseOrderLineChange";

            }
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
