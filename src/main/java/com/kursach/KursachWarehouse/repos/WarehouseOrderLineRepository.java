package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseOrderLineRepository extends CrudRepository<WarehouseOrderLine,Integer> {

    List<WarehouseOrderLine> findWarehouseOrderLineByWarehouseOrder_Id(Long tag);
    WarehouseOrderLine findByWarehouseOrder_Id(Long tag);
    List<WarehouseOrderLine> findByUser_Id(Long tag);
    Integer deleteById(Long tag);
}
