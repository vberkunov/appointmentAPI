create sequence hibernate_sequence start 1 increment 1;
create table contract (
    id int8 not null,
    confirmation boolean not null,
    lesson_id int8 not null,
    student_id int8 not null,
    primary key (id)
                      );

create table lesson (
    id int8 not null,
    description varchar(255),
    price varchar(255),
    reserved_date date,
    time_from time,
    time_to time,
    teacher_id int8 not null,
    primary key (id)
                    );

create table roles (
    id int8 not null,
    name varchar(255),
    primary key (id)
                   );

create table student (
    id int8 not null,
    user_id int8,
    primary key (id)
                     );

create table teacher (
    id int8 not null,
    user_id int8,
    primary key (id)
                     );

create table users (
    id int8 not null,
    created timestamp,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    status varchar(255),
    updated timestamp,
    username varchar(255),
    role_id int8 not null,
    primary key (id)
                   );

alter table if exists contract
    add constraint FKisehfd4s2q82dhc47blj33u0y
    foreign key (lesson_id) references lesson;

alter table if exists contract
    add constraint FK14c8jlug1v4bjjut2qm9k1oa2
    foreign key (student_id) references student;

alter table if exists lesson
    add constraint FK9yhaoqrjxt5gwmn6icp1lf35n
    foreign key (teacher_id) references teacher;

alter table if exists student
    add constraint FKk0thg920a3xk3v59yjbsatw1l
    foreign key (user_id) references users;

alter table if exists teacher
    add constraint FKcp1vpkh4bh0qux9vtvs0fkwrn
    foreign key (user_id) references users;
alter table if exists users
    add constraint FKp56c1712k691lhsyewcssf40f
    foreign key (role_id) references roles;