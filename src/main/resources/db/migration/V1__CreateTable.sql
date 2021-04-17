create sequence hibernate_sequence;
alter sequence hibernate_sequence owner to postgres;
create table if not exists post
(
    id        bigserial
        primary key not null ,
    author_name    varchar(140),
    publish_date    varchar(2048),
    text varchar(140),
    title     varchar(140)
) partition by range (id);

create table if not exists post_partition1 partition of post for values from (1) to (3);
create table if not exists post_partition2 partition of post for values from (3) to (10000);
create table if not exists post_partition3 partition of post for values from (10000) to (1000000);