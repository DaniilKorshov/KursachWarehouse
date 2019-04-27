package com.kursach.KursachWarehouse.repos;

import com.kursach.KursachWarehouse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String username);
}
