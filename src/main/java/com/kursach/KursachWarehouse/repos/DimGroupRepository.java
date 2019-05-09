package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.Dimgroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DimGroupRepository extends CrudRepository<Dimgroup,Integer> {
    List<Dimgroup> findById(Long tag);
    Dimgroup findByWidthAndLengthAndWeightAndColorAndConfig(double tag1,double tag2,double tag3,String tag4,String tag5);
    Integer deleteById(Long tag);
}
