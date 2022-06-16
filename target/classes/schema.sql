create sequence hibernate_sequence;

create table article
(
    id              long    not null,
    title         	varchar(255),
    content         varchar(255)
);

alter table article add constraint article_pk primary key (id);