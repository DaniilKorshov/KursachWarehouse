package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.InventSum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventSumRepository extends CrudRepository<InventSum,Integer> {

    List<InventSum> findByInvent_Id(Long tag);
    InventSum findByCell_Id(Long tag);
    InventSum findById(Long tag);
    Integer deleteById(Long tag);
}
