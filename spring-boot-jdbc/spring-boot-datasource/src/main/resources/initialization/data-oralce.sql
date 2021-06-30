alter table SYS_J_USER
    disable all triggers;
insert into SYS_J_USER (user_id, user_code, user_name)
values (1, 'oracle', 'oracle');
commit;
alter table SYS_J_USER
    enable all triggers;
