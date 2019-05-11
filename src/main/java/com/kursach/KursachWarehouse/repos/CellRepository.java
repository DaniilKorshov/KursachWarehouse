package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.Cell;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CellRepository extends CrudRepository<Cell,Integer> {
    List<Cell> findByWeightGreaterThanEqual(Double tag);
    List<Cell> findByWarehouse_Id(Long tag);
    Cell findById(Long tag);
    Integer deleteById(Long tag);
}
