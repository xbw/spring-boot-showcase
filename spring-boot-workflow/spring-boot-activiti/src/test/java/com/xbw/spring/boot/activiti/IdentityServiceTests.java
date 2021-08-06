package com.xbw.spring.boot.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IdentityServiceTests {
    @Autowired
    IdentityService identityService;

    @Test
    @Order(1)
    public void idGenerator() {
        User user = new UserEntity();
        user.setFirstName("first");
        user.setLastName("last");
        user.setPassword("password");
        identityService.saveUser(user);
    }

    @Test
    @Order(1)
    public void saveUser() {
        User user = new UserEntity();
        user.setId("root");
        user.setPassword("root");
        identityService.saveUser(user);

        user = new UserEntity();
        user.setId("sa");
        user.setPassword("sa");
        identityService.saveUser(user);

        user = new UserEntity();
        user.setId("sa2");
        user.setPassword("sa");
        identityService.saveUser(user);
    }

    @Test
    @Order(2)
    public void deleteUser() {
        identityService.deleteUser("sa2");
    }

    @Test
    @Order(2)
    void userInfo() {
        identityService.setUserInfo("root", "birthday", "1970-01-01");
    }

    @Test
    @Order(3)
    public void saveGroup() {
        Group group = new GroupEntity();
        group.setId("admin");
        group.setName("Administrator");
        identityService.saveGroup(group);

        group = new GroupEntity();
        group.setId("user");
        group.setName("User");
        identityService.saveGroup(group);

        group = new GroupEntity();
        group.setId("user2");
        group.setName("User");
        identityService.saveGroup(group);
    }

    @Test
    @Order(4)
    public void deleteGroup() {
        identityService.deleteGroup("user2");
    }

    @Test
    @Order(5)
    public void saveMembership() {
        identityService.createMembership("root", "admin");
        identityService.createMembership("sa", "admin");
        identityService.createMembership("sa", "user");
    }

    @Test
    @Order(6)
    public void deleteMembership() {
        identityService.deleteMembership("sa", "user");
    }

}