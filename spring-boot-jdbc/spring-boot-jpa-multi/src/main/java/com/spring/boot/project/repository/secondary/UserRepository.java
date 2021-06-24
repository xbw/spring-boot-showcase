package com.spring.boot.project.repository.secondary;

import com.spring.boot.project.model.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
