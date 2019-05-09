package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.WarehouseOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseOrderRepository extends CrudRepository<WarehouseOrder,Integer> {
    List<WarehouseOrder> findById(Long tag);
    Integer deleteById(Long tag);
}
