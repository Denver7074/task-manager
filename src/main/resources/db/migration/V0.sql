create table if not exists task_status
(
    id                 bigserial primary key,
    name               varchar(20) not null,
    code               varchar(20) not null,
    created_date       timestamp,
    last_modified_date timestamp
);

create table if not exists task_priority
(
    id                 bigserial primary key,
    name               varchar(20) not null,
    code               varchar(20) not null,
    created_date       timestamp,
    last_modified_date timestamp
);

create table if not exists role
(
    id                 bigserial primary key,
    name               varchar(20) not null,
    code               varchar(20) not null,
    created_date       timestamp,
    last_modified_date timestamp
);


create table if not exists task_user
(
    id      bigserial primary key,
    role_id bigserial references role,
    task_id bigint,
    user_id bigint
);


create table if not exists person
(
    id                 bigserial primary key,
    first_name         varchar(50),
    second_name        varchar(50),
    last_name          varchar(50),
    date_birth         date,
    email              varchar(50),
    password           varchar(50),
--     task_user_id       bigint,
    created_date       timestamp,
    last_modified_date timestamp
--     foreign key (task_user_id) references task_user (id)
);

create table if not exists task
(
    id                 bigserial primary key,
    header             varchar(50),
    description        varchar(500),
    status_id          bigserial not null references task_status,
    priority_id        bigserial not null references task_priority,
    date_execution     timestamp,
--     task_user_id       bigint,
    created_date       timestamp,
    last_modified_date timestamp
--     foreign key (task_user_id) references task_user (id)
);

