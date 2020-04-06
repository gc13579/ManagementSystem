--######################
--创建数据库
drop database if exists ims;
create database if not exists ims default charset utf8;

--#####################


--#########################
--创建部门表
drop table if exists t_dept;
create table t_dept(
   t_dept_no  char(5) primary key,
   t_dept_name varchar(20) not null,
   t_dept_loc  varchar(30) not null,
   t_dept_manager varchar(20),
   t_create_time  datetime,
   t_description varchar(200)
);

--初始化
insert into t_dept values('A1006','总经办','101室','李梅',sysdate(),'这是一个总管公司的部门');
insert into t_dept values('A1007','市场营销部','102室','韩梅梅',sysdate(),'这是一个对公司市场统计的部门');
insert into t_dept values('A1008','教学部','103室','张三丰',sysdate(),'这是一个管理公司教学的部门');

--员工表
drop table if exists t_employee;
create table t_employee(
	id int primary key auto_increment,
	t_emp_no char(5) not null,
	t_emp_name varchar(20) not null,
	t_emp_dept char(5) not null,
	t_sex varchar(1) not null,
	t_education varchar(20) not null,
	t_email varchar(20) not null,
	t_phone varchar(20) not null,
	t_entry_time datetime,
	t_create_time datetime
);
--alter table t_employee add foreign key (t_emp_dept) references t_dept(t_dept_no);

insert into t_employee values(null,'B1001','李梅','总经办','女','专科','123456789@qq.com','12345678998','2014-05-23',sysdate());
insert into t_employee values(null,'B1002','韩梅梅','市场营销部','女','专科','123456799@qq.com','12345678898','2015-04-23',sysdate());
insert into t_employee values(null,'B1003','张三丰','教学部','男','专科','123457789@qq.com','12945678998','2016-06-23',sysdate());
insert into t_employee values(null,'B1004','张四丰','教学部','男','专科','122457789@qq.com','12945674998','2016-06-29',sysdate());
insert into t_employee values(null,'B1000','老板','总经办','男','博士','122457789@qq.com','12945674998','2016-06-29',sysdate());

--请假表

drop table if exists t_holiday;
create table t_holiday(
	id int primary key auto_increment,
	t_holiday_no varchar(5),
	t_holiday_user varchar(20) not null,
	t_holiday_type varchar(20)not null,
	t_holiday_bz varchar(100) not null,
	t_start_time datetime,
	t_end_time datetime,
	t_holiday_states varchar(20),
	t_create_time datetime
);
insert into t_holiday values (null,'QJ100','李梅','事假','家里有事','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ101','李梅','婚假','结婚','2016-07-23','2016-08-25','已提交','2016-07-22');
insert into t_holiday values (null,'QJ102','韩梅梅','丧假','爷爷去世','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ103','韩梅梅','年假','申请年假','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ103','韩梅梅','调休','调休','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ103','韩梅梅','事假','有事','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ104','张三丰','调休','和同事调休','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ105','张三丰','病假','发烧去医院挂水','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ106','张四丰','年假','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ107','张四丰','事假','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ108','张四丰','婚嫁','去度蜜月','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ109','张四丰','年假','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ110','张四丰','调休','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ111','老板','事假','去旅行','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ112','老板','婚嫁','去旅行','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ113','老板','病假','去旅行','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ114','老板','调休','去旅行','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ115','老板','年假','去旅行','2016-06-23','2016-06-25','草稿','2016-06-22');
--用户表
drop table if exists t_user;
create table t_user(
   t_user_id  int primary key auto_increment,
   t_user_account varchar(20) not null,
    t_user_pwd   varchar(20) not null,
   t_user_name varchar(20) not null,
   t_role_id int,
   t_user_state varchar(5),
   t_create_time date
);
alter table t_user add foreign key (t_role_id) references t_role(t_role_id);
 
insert into t_user values (null,'admin','123456','张三丰',1,'正常',now());
insert into t_user values (null,'user1','123456','李梅',2,'正常',now());
insert into t_user values (null,'freshAdmin','123456','张四丰',3,'正常',now());
insert into t_user values (null,'root','123456','老板',4,'正常',now());


--请假表
drop table if exists t_holiday;
create table t_holiday(
	id int primary key auto_increment,
	t_holiday_no char(5),
	t_holiday_user varchar(20) not null,
	t_holiday_type varchar(20)not null,
	t_holiday_bz varchar(100) not null,
	t_start_time datetime,
	t_end_time datetime,
	t_holiday_states varchar(20),
	t_create_time datetime,
);
insert into t_holiday values (null,'QJ100','李梅','事假','家里有事','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ101','李梅','婚假','结婚','2016-07-23','2016-08-25','已提交','2016-07-22');
insert into t_holiday values (null,'QJ102','韩梅梅','丧假','爷爷去世','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ103','韩梅梅','年假','申请年假','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ103','韩梅梅','调休','调休','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ103','韩梅梅','事假','有事','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ104','张三丰','调休','和同事调休','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ105','张三丰','病假','发烧去医院挂水','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ106','张四丰','年假','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ107','张四丰','事假','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ108','张四丰','婚嫁','去度蜜月','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ109','张四丰','年假','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ110','张四丰','调休','去度蜜月','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ111','老板','事假','去旅行','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ112','老板','婚嫁','去旅行','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ113','老板','病假','去旅行','2016-06-23','2016-06-25','草稿','2016-06-22');
insert into t_holiday values (null,'QJ114','老板','调休','去旅行','2016-06-23','2016-06-25','已提交','2016-06-22');
insert into t_holiday values (null,'QJ115','老板','年假','去旅行','2016-06-23','2016-06-25','草稿','2016-06-22');
  

--报销表
drop table if exists t_reimbursement;
create table t_reimbursement(
    id int primary key auto_increment,
	t_reim_no varchar(5) not null,
	t_reim_person varchar(20) not null,
  	t_reim_type  varchar(20) not null,
   	t_reim_money double,
   	t_reim_time  date,
   	t_reim_state varchar(10),
   	t_reim_abstract varchar(100)
);
insert into t_reimbursement values(null,'BX100','李梅','办公费','100',sysdate(),'草稿','办公工具');
insert into t_reimbursement values(null,'BX101','李梅','差旅费','1000',sysdate(),'草稿','去外地出差');
insert into t_reimbursement values(null,'BX102','李梅','招待费','300',sysdate(),'草稿','给客户解决住宿');
insert into t_reimbursement values(null,'BX103','韩梅梅','办公费','100',sysdate(),'草稿','办公工具');
insert into t_reimbursement values(null,'BX104','韩梅梅','差旅费','600',sysdate(),'已提交','外出管理仓库');
insert into t_reimbursement values(null,'BX105','韩梅梅','招待费','800',sysdate(),'草稿','陪客户吃饭');
insert into t_reimbursement values(null,'BX106','张三丰','差旅费','500',sysdate(),'已提交','外出谈项目');
insert into t_reimbursement values(null,'BX107','张三丰','招待费','1000',sysdate(),'草稿','准备会议场所');
insert into t_reimbursement values(null,'BX108','张三丰','办公费','100',sysdate(),'草稿','办公工具');
insert into t_reimbursement values(null,'BX109','老板','办公费','1000',sysdate(),'已提交','办公工具');
insert into t_reimbursement values(null,'BX110','老板','招待费','1000',sysdate(),'草稿','办公工具');
insert into t_reimbursement values(null,'BX111','老板','差旅费','1000',sysdate(),'草稿','办公工具');




--角色表
drop table if exists t_role;
create table t_role(
	t_role_id int primary key auto_increment,
	t_role_name varchar(30) not null,
	t_create_time date
);
--角色表数据
insert into t_role(t_role_id,t_role_name,t_create_time) values(1,'管理员',now());
insert into t_role(t_role_id,t_role_name,t_create_time) values(2,'普通用户',now());
insert into t_role(t_role_id,t_role_name,t_create_time) values(3,'实习管理员',now());
insert into t_role(t_role_id,t_role_name,t_create_time) values(4,'超级管理员',now());


--权限表
drop table if exists t_permissions;
create table t_permissions(
	t_id int primary key auto_increment,
	t_role_id int,
	t_menu_id int,
	t_create_time date
); 
alter table t_permissions add foreign key (t_role_id) references t_role(t_role_id);
alter table t_permissions add foreign key (t_menu_id) references t_menu(t_menu_id);

--权限表数据
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,1,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,2,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,3,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,4,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,5,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,6,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,7,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,8,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,9,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,10,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,11,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,4,12,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,1,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,2,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,3,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,4,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,5,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,6,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,7,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,9,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,1,12,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,1,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,2,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,3,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,4,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,6,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,7,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,9,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,3,12,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,1,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,2,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,3,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,6,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,7,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,9,now());
insert into t_permissions(t_id,t_role_id,t_menu_id,t_create_time) values(null,2,12,now());


--菜单表
drop table if exists t_menu;
create table t_menu(
   t_menu_id int  primary key auto_increment,
   t_menu_name varchar(50) not null,
   t_href_url varchar(200) ,
   t_parent_id int,
   t_create_time date
);
alter table t_menu add foreign key (t_parent_id) references t_menu(t_menu_id);

--菜单表添加数据
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(1,'人事管理','',null,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(2,'财务管理','',null,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(3,'系统管理','',null,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(4,'部门管理','njwb/dept/dept.jsp',1,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(5,'员工管理','njwb/employee/employee.jsp',1,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(6,'请假管理','njwb/holiday/holiday.jsp',1,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(7,'报销管理','njwb/reimbursement/reimbursement.jsp',2,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(8,'账户维护','njwb/user/user.jsp',3,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(9,'密码重置','njwb/passwordReset.jsp',3,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(10,'角色管理','njwb/role/role.jsp',3,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(11,'权限管理','njwb/permissions/permissions.jsp',3,now());
insert into t_menu(t_menu_id,t_menu_name,t_href_url,t_parent_id,t_create_time) values(12,'系统退出','user/quit.do',3,now());
--###########################