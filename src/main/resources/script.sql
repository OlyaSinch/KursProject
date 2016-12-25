create table group_table (
 group_id int not null,
 group_name varchar(255) not null,
 primary key (group_id)
);

create table status_table (
 status_id int not null,
 status_name varchar(255) not null,
 primary key (status_id)
);

create table user_table (
 user_id int not null,
 user_login varchar(255) not null,
 user_password varchar(255) not null,
 user_name varchar(255) not null,
 user_lastname varchar(255) not null,
 fk_group_id int not null,
 primary key (user_id),
 foreign key (fk_group_id) references group_table (group_id)
);

create table order_table (
 order_id int not null,
 order_title varchar(255) not null,
 order_task varchar(255) not null,
 order_price int not null,
 fk_status_id int not null,
 primary key (order_id),
 foreign key (fk_status_id) references status_table (status_id)
);

create table user_order_relation_table (
 uor_id int not null,
 fk_creator_id int null,
 fk_developer_id int null,
 fk_order_id int null,
 primary key (uor_id),
 foreign key (fk_creator_id) references user_table (user_id),
 foreign key (fk_developer_id) references user_table (user_id),
 foreign key (fk_order_id) references order_table (order_id)
);

CREATE SEQUENCE group_sequence
 START WITH 1
 INCREMENT BY 1
 NOCYCLE;
 
CREATE SEQUENCE status_sequence
 START WITH 1
 INCREMENT BY 1
 NOCYCLE;
 
CREATE SEQUENCE user_sequence
 START WITH 1
 INCREMENT BY 1
 NOCYCLE;
 
CREATE SEQUENCE order_sequence
 START WITH 1
 INCREMENT BY 1
 NOCYCLE;
 
CREATE SEQUENCE user_order_relation_sequence
 START WITH 1
 INCREMENT BY 1
 NOCYCLE;
 
CREATE UNIQUE INDEX LoginIndex ON user_table(user_login);