create table if not exists LOAN (
    id serial primary key not null,
    loan_balance number not null,
    origination_date date not null,
    property_id serial not null,
    loan_client_id serial not null
);

create table if not exists CLIENT (
    id serial primary key not null,
    name varchar(255) not null,
    type varchar(100) not null,
    status varchar(100) not null
);

create table if not exists PROPERTY (
    id serial primary key not null,
    address varchar(255) not null,
    city varchar(100) not null,
    client_Id serial not null
);