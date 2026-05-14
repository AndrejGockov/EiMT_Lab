create table users
(
    id         bigserial primary key,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    username   varchar(255) not null unique,
    email      varchar(255) not null unique,
    password   varchar(255) not null
);


alter table users add column role varchar(50) not null default 'USER';


alter table users
    alter column created_at set default NOW(),
    alter column updated_at set default NOW();

