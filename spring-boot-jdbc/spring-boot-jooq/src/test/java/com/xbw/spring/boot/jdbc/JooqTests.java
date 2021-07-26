package com.xbw.spring.boot.jdbc;

import com.xbw.spring.boot.project.jooq.Tables;
import com.xbw.spring.boot.project.jooq.tables.SysJUser;
import com.xbw.spring.boot.project.jooq.tables.Users;
import com.xbw.spring.boot.project.jooq.tables.records.SysJUserRecord;
import com.xbw.spring.boot.project.jooq.tables.records.UsersRecord;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectWhereStep;
import org.jooq.impl.DefaultDSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xbw
 */
//@org.springframework.boot.test.autoconfigure.jooq.JooqTest
@SpringBootTest
public class JooqTests {
    @Autowired
    DefaultDSLContext dsl;

    @BeforeEach
    void setUp() {
//        System.setProperty("org.jooq.no-logo", "true");
    }

    @Test
    public void jooq() {
        dsl.meta().getSchemas().forEach(System.out::println);
    }

    @Test
    public void selectFrom() {
        SelectWhereStep<Record> records = dsl.selectFrom("users");
        List<SysJUserRecord> sysJUsers = records.fetchInto(SysJUserRecord.class);
        sysJUsers.forEach(record -> {
            System.out.printf("id:%s, code:%s, name:%s%n", record.getUserId(), record.getUserCode(), record.getUserName());
        });
    }

    @Test
    void from() {
        Result<Record> result = dsl.select().from(Tables.SYS_J_USER).fetch();
        result.forEach(record -> {
            Long id = record.getValue(SysJUser.SYS_J_USER.USER_ID);
            String code = record.getValue(SysJUser.SYS_J_USER.USER_CODE);
            String name = record.getValue(SysJUser.SYS_J_USER.USER_NAME);
            System.out.printf("id:%s, code:%s, name:%s%n", id, code, name);
        });
    }

    @Test
    void fetchOne() {
        SysJUserRecord sysJUserRecord = dsl.fetchOne(Tables.SYS_J_USER, SysJUser.SYS_J_USER.USER_ID.eq(1L));
        Long id = sysJUserRecord.getValue(SysJUser.SYS_J_USER.USER_ID);
        String code = sysJUserRecord.getValue(SysJUser.SYS_J_USER.USER_CODE);
        String name = sysJUserRecord.getValue(SysJUser.SYS_J_USER.USER_NAME);
        System.out.printf("id:%s, code:%s, name:%s%n", id, code, name);
    }

    @Test
    void delete() {
        dsl.fetchOne(Tables.SYS_J_USER, SysJUser.SYS_J_USER.USER_ID.eq(1L))
                .delete();

        dsl.delete(Tables.USERS)
                .where(Users.USERS.USER_CODE.like("insert%"))
                .execute();
    }

    @Test
    void insert() {
        UsersRecord usersRecord = dsl.newRecord(Tables.USERS);
        usersRecord.setUserId(2L);
        usersRecord.setUserCode("insert");
        usersRecord.setUserName("insert");
        usersRecord.insert();

        SysJUserRecord sysJUserRecord = dsl.newRecord(Tables.SYS_J_USER);
        sysJUserRecord.setUserCode("insert");
        sysJUserRecord.setUserName("insert");
        sysJUserRecord.insert();
    }

    @Test
    void insertInto() {
        dsl.insertInto(Tables.USERS)
                .values(3L, "insertInto", "insertInto")
                .onDuplicateKeyIgnore()
                .execute();

        dsl.insertInto(Tables.USERS)
                .set(Users.USERS.USER_ID, 4L)
                .set(Users.USERS.USER_CODE, "insertInto")
                .set(Users.USERS.USER_NAME, "insertInto")
                .onDuplicateKeyIgnore()
                .execute();
    }

    @Test
    void returning() {
        SysJUserRecord sysJUserRecord = dsl.insertInto(Tables.SYS_J_USER)
                .set(SysJUser.SYS_J_USER.USER_CODE, "returning")
                .set(SysJUser.SYS_J_USER.USER_NAME, "returning")
                .returning()
                .fetchOne();
        System.out.println("sysJUserRecord:" + sysJUserRecord.getUserId());
    }
}
