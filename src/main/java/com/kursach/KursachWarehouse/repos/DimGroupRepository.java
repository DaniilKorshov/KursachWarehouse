package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.Dimgroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DimGroupRepository extends CrudRepository<Dimgroup,Integer> {
    List<Dimgroup> findById(Long tag);
}
