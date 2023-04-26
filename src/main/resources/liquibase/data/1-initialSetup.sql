-- Initial setup of the database

alter table if exists empl drop constraint if exists fk_empl_orga_id;
alter table if exists empl drop constraint if exists fk_empl_person_id;
alter table if exists empl_role drop constraint if exists fk_empl_role_role_id;
alter table if exists empl_role drop constraint if exists fk_empl_role_empl_id;
alter table if exists role_permission drop constraint if exists fk_role_permission_permission_id;
alter table if exists role_permission drop constraint if exists fk_role_permission_role_id;

drop table if exists empl cascade;
drop table if exists empl_role cascade;
drop table if exists orga cascade;
drop table if exists permission cascade;
drop table if exists person cascade;
drop table if exists role cascade;
drop table if exists role_permission cascade;

drop sequence if exists empl_seq;
drop sequence if exists orga_seq;
drop sequence if exists permission_seq;
drop sequence if exists person_seq;
drop sequence if exists role_seq;

create sequence empl_seq start with 1 increment by 50;
create sequence orga_seq start with 1 increment by 50;
create sequence permission_seq start with 1 increment by 50;
create sequence person_seq start with 1 increment by 50;
create sequence role_seq start with 1 increment by 50;

create table empl (id bigint not null, orga_id bigint, person_id bigint, primary key (id));
create table empl_role (empl_id bigint not null, role_id bigint not null);
create table orga (id bigint not null, name varchar(255), primary key (id));
create table permission (id bigint not null, name varchar(255), primary key (id));
create table person (id bigint not null, first_name varchar(255), last_name varchar(255), primary key (id));
create table role (id bigint not null, name varchar(255), primary key (id));
create table role_permission (role_id bigint not null, permission_id bigint not null);
alter table if exists empl add constraint fk_empl_orga_id foreign key (orga_id) references orga;
alter table if exists empl add constraint fk_empl_person_id foreign key (person_id) references person;
alter table if exists empl_role add constraint fk_empl_role_role_id foreign key (role_id) references role;
alter table if exists empl_role add constraint fk_empl_role_empl_id foreign key (empl_id) references empl;
alter table if exists role_permission add constraint fk_role_permission_permission_id foreign key (permission_id) references permission;
alter table if exists role_permission add constraint fk_role_permission_role_id foreign key (role_id) references role;
