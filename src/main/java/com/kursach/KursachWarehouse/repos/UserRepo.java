package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.User;
import com.kursach.KursachWarehouse.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String username);
    List<User> findByUserRoles(UserRole role);
}
