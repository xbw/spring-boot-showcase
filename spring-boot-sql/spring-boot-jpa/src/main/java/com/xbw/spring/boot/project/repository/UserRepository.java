package com.xbw.spring.boot.project.repository;

import com.xbw.spring.boot.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
