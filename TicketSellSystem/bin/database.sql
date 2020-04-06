create table t_user(
id int primary key auto_increment,
username varchar(20),
password varchar(20),
idcard varchar(18) unique,
money double,
state int
phonenum varchar(11) unique,
);
insert into t_user values (null,'gc','123','362203199806090418',0,'17327758862',1);
insert into t_user values (null,'root','root','000000000000000000',0,'00000000000',0);



create table t_ticket(
id int primary key auto_increment,
trainnum varchar(20),
startplace varchar(20),
endplace varchar(20),
starttime varchar(20),
count int,
state int,
price double
);
insert into t_ticket values (null,'南京','上海','2019/11/1',300,"可购买",152,'G722');
insert into t_ticket values (null,'Z126','上海','苏州','2019/10/12',100,"不可购买",45);
insert into t_ticket values (null,'K1192','南京','南宁','2019/10/1',200,"可购买",450);
insert into t_ticket values (null,'T452','北京','铁岭','2019/11/19',150,"可购买",90);
insert into t_ticket values (null,'D212','南京','南通','2019/6/30',100,"不可购买",152);
insert into t_ticket values (null,'G452','南京','上海','2019/11/1',260,"可购买",152);
insert into t_ticket values (null,'G134','南京','杭州','2019/11/1',270,"可购买",148);


create table t_purchase_record(
p_id int primary key auto_increment,
u_id int,
t_id int,
buytime varchar(20),
buystate varchar(20),
foreign key (uid) references t_user(id),
foreign key (t_id) references t_ticket(id)
);


create table t_recharge_apply(
r_id int primary key auto_increment,
u_id int,
applymoney double,
applytime varchar(20),
applystate varchar(20),
foreign key (u_id) references t_user(id)
);

