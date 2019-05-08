package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.WarehouseOrderLine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseOrderLineRepository extends CrudRepository<WarehouseOrderLine,Integer> {
    List<WarehouseOrderLine> findByWarehouseOrder(Long tag);
}
