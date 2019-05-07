package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.Warehouse;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WarehouseRepository extends CrudRepository <Warehouse,Integer> {
    List<Warehouse> findByWarehouseAddressContaining(String tag);
}
