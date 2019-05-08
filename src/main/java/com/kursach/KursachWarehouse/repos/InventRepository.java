package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.Invent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventRepository extends CrudRepository<Invent,Integer> {
    List<Invent> findByNameContaining(String tag);
}
