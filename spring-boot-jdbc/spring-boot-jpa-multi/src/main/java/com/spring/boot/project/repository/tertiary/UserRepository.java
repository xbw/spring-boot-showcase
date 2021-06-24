package com.spring.boot.project.repository.tertiary;

import com.spring.boot.project.model.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepositoryTertiary")
public interface UserRepository extends JpaRepository<User, Long> {
}
