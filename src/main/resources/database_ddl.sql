create table public.users
(
    id               bigserial
        constraint users_pk
            primary key,
    firstname        varchar(25),
    second_name      varchar(25),
    age              integer,
    telephone_number varchar(25),
    email            varchar(25),
    created          timestamp default now() not null,
    updated          timestamp default now() not null,
    sex              varchar(1),
    is_deleted       boolean   default false
);

create table public.security
(
    id       integer                                       not null
        constraint security_pk
            primary key,
    login    varchar(20)                                   not null,
    password varchar(20)                                   not null,
    role     varchar(20) default 'USER'::character varying not null,
    created  timestamp   default now()                     not null,
    updated  timestamp   default now(),
    user_id  bigint                                        not null
        unique
        constraint security_users_id_fk
            references public.users
            on update cascade on delete cascade
);

create table public.product
(
    id      bigserial
        constraint product_pk
            primary key,
    name    varchar(50)      not null,
    price   double precision not null,
    created timestamp default now(),
    updated timestamp default now()
);

create table public.l_users_product
(
    id         integer not null
        constraint l_users_product_pk
            primary key,
    user_id    bigint  not null
        constraint l_users_product_users_id_fk
            references public.users
            on update cascade on delete cascade,
    product_id bigint  not null
        constraint l_users_product_product_id_fk
            references public.product
            on update cascade on delete cascade,
    created    timestamp default now(),
    updated    timestamp default now()
);


