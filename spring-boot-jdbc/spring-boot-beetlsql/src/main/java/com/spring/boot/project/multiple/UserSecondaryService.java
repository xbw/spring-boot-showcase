package com.spring.boot.project.multiple;

import com.spring.boot.project.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "multi")
@Service
public class UserSecondaryService extends UserService {
    @Autowired
    @Qualifier("secondarySqlManager")
    SQLManager sqlManager;
}
