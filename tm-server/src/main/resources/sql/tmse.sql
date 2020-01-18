create database if not exists tmse;
use tmse;

create table role (
    id varchar(255) not null,
    role_name varchar(255) not NULL,
    PRIMARY KEY (id)
) engine = innodb default charset=utf8;

create table app_user (
    id varchar(255) not null,
    login varchar(255) not null,
    hash_password varchar(255) not null,
    role_id varchar(255) not null,
    primary KEY (id, login),
    constraint role_id_fk
    foreign KEY (role_id) references role(id)
) engine = innodb default charset=utf8;

create table status (
    id varchar(255) not null,
    status_name varchar(255) not null,
    primary key (id)
) engine = innodb default charset=utf8;

create table project (
    id varchar(255) not null,
    project_name varchar(255) not null,
    description varchar(255) not null,
    date_start datetime not null,
    date_finish datetime not null,
    user_id varchar(255) not null,
    status_id varchar(255) not null,
    system_time BIGINT not null,
    primary KEY (id),
    constraint user_id_fk
    foreign key (user_id) references app_user(id),
    constraint status_id_fk
    foreign key (status_id) references status(id)
) engine = innodb default charset=utf8;

create table task (
    id varchar(255) not null,
    task_name varchar(255) not null,
    description varchar(255) not null,
    date_start datetime not null,
    date_finish datetime not null,
    project_id varchar(255) not null,
    user_id varchar(255) not null,
    status_id varchar(255) not null,
    system_time BIGINT not null,
    primary KEY (id),
    constraint project_id_fk
    foreign key (project_id) references project(id),
    constraint user_id_fk_2
    foreign key (user_id) references app_user(id),
    constraint status_id_fk_2
    foreign key (status_id) references status(id)
) engine = innodb default charset=UTF8;

create table session (
    id varchar(255) not null,
    time_stamp bigint not null,
    user_id varchar(255) not null,
    signature varchar(255) not null,
    primary key (id),
    constraint user_id_fk_3
    foreign key (user_id) references app_user(id)
) engine = innodb default charset=UTF8;

insert into role(id, role_name) VALUES ('43d97552-0b01-11ea-8d71-362b9e155667', 'USER');
insert into role(id, role_name) VALUES ('43d97a20-0b01-11ea-8d71-362b9e155667', 'ADMIN');

insert into app_user(id, login, hash_password, role_id) values('a40beb53-ae1a-4ff2-8c30-ee77ea96f2a1', 'root', '63a9f0ea7bb98050796b649e85481845', '43d97552-0b01-11ea-8d71-362b9e155667');
insert into app_user(id, login, hash_password, role_id) values('1152564a-c6ad-42b0-9b4c-332f3c53e311', 'user', 'ee11cbb19052e40b07aac0ca060c23ee', '43d97a20-0b01-11ea-8d71-362b9e155667');
