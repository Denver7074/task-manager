create table if not exists task_status
(
    id   bigserial primary key,
    name varchar(20) not null,
    code varchar(20) not null
);

create table if not exists task_priority
(
    id   bigserial primary key,
    name varchar(20) not null,
    code varchar(20) not null
);

create table if not exists person
(
    id                 bigserial primary key,
    first_name         varchar(50) not null,
    second_name        varchar(50),
    last_name          varchar(50) not null,
    date_birth         date,
    email              varchar(50) not null,
    password           varchar(50) not null,
    created_date       timestamp   not null,
    last_modified_date timestamp   not null
);

create table if not exists task
(
    id                 bigserial primary key,
    header             varchar(50)  not null,
    description        varchar(500) not null,
    priority_id        bigserial    not null references task_priority,
    author_id          bigserial    not null references person,
    created_date       timestamp    not null,
    last_modified_date timestamp    not null
);

create table if not exists task_executor
(
    id             bigserial primary key,
    task_id        bigserial references task,
    date_execution date      not null,
    status_id      bigserial not null references task_status,
    person_id      bigserial not null references person
);

create table if not exists comment
(
    id        bigserial primary key,
    task_id   bigserial not null references task,
    person_id bigserial not null references person
);
