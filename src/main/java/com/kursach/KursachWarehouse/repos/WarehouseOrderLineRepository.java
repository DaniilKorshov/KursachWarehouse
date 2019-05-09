package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.WarehouseOrderLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseOrderLineRepository extends CrudRepository<WarehouseOrderLine,Integer> {

    List<WarehouseOrderLine> findWarehouseOrderLineByWarehouseOrder_Id(Long tag);
    Integer deleteById(Long tag);
}
