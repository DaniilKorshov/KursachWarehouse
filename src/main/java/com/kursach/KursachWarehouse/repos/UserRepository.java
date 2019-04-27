package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
