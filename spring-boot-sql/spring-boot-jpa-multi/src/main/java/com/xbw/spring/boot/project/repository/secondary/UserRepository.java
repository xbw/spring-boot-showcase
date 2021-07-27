package com.xbw.spring.boot.project.repository.secondary;

import com.xbw.spring.boot.project.model.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
