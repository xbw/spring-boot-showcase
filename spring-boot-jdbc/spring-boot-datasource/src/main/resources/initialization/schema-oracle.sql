drop table SYS_J_USER cascade constraints;
create table SYS_J_USER
(
    user_id   NUMBER(19) not null,
    user_code VARCHAR2(255 CHAR),
    user_name VARCHAR2(255 CHAR)
);

alter table SYS_J_USER
    add primary key (USER_ID);
