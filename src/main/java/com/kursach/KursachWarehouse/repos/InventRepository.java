package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.Invent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventRepository extends CrudRepository<Invent,Integer> {
    List<Invent> findByNameContaining(String tag);
    Invent findByNameAndDimgroup_Id(String tag,Long tag2);
    List<Invent> findByDimgroup_Id(Long tag);
    Invent findById(Long tag);
    Integer deleteById(Long tag);
}
