CREATE DATABASE POINT;
create table point_history (id bigint not null, created_at datetime, last_modified_at datetime, active bit not null, place_id binary(255), point_cause varchar(255), review_id binary(255), user_id binary(255), primary key (id)) engine=InnoDB
