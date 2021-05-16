1.DDL(Data Definition Language):数据定义语言，用来定义数据库对象：库、表、列等。功能：
创建、删除、修改库和表结构。
2.DML(Data Manipulation Language):数据操作语言，用来定义数据库记录:增、删、改表记录。
3.DCL(Data Control Language):数据控制语言，用来定义访问权限和安全级别。（不重要）
4.DQL(Data Query Language):数据查询语言，用来查询记录。也是本章学习的重点。

create database test01 CHARACTER SET utf8mb4;
一、对数据库的操作（增删查改）:
查看所有数据库：SHOW DATABASES;
删除数据库: DROP DATABASE 数据库名;

创建表：
CREATE TABLE student (
	id INT(10) primary key,
	name VARCHAR (10),
	age INT (10) NOT NULL,
	gander varchar(2)
);

查看当前数据库中所有表: SHOW TABLES;
查看表结构: DESC 表名;
删除表: DROP table 表名;

修改表之添加列: ALTER TABLE 表名 add (列名 列类型，...，列名 列类型);

二、DML(数据操作语言)语法（重要）
插入数据(一次插入就是插入一行)
insert into 表名 (列名1，列名2，列名3) values (列值1，列值2，列值3);
insert into stu (id,name,age,gander) values (2,'李华',19,'男');

修改某列的全部值，不加where会修改表中所有数据
update 表名 set 列名1=列值2;
UPDATE stu set age=22;
UPDATE stu set age=23,name='张楠';

删除数据(删除整行)，不加where会删除表中所有记录
delete from 表名 (where 条件);

三、建表约束
NOT NULL 	非空约束
UNIQUE 		唯一约束，取值不允许重复,
PRIMARY KEY 主键约束（主关键字），自带非空、唯一、索引
FOREIGN KEY 外键约束（外关键字）
DEFAULT 	默认值（缺省值）

四、查询语言
查询指定列：select 列1，列2 from 表名;
字符串类型做连续运算：select concat(列名1，列名2) from 表名;
给列名起别名:select 列名1 (as) 别名1,列名2 (as) 别名2 from 表名;

条件查询：select * from 表名 where 列名＝指定值;
模糊查询：select * from 表名 where 列名 like ‘张_’; _任意字符，%代表匹配0~n个字符

排序
升序: select * form 表名 order by 列名 (ASC );
降序: select * from 表名 order by 列名 DESC;

聚合函数
count：select count(列名) from 表名;
max：select max(列名) from 表名;
min：select min(列名) from 表名;
sum：select sum(列名) from 表名;
avg：select avg(列名) from 表名;

分组查询
select 分组列，聚合函数 from 表名 where 条件 group by 分组列 having 聚合函数或列名(条件)；
select gander,avg(age) avg_age,sum(age) sum_age from student GROUP BY gander HAVING gander = '男'

limit子句(mysql中独有的语法)
select * from 表名 limit 4，3; 表示起始行为第5行，一共查询3行记录

多表查询
内连接
内连接查询操作只列出与连接条件匹配的数据行，使用INNER JOIN或者直接使用JOIN 进行连接
select * from table_a join table_b;(保留所有结果，笛卡尔积)
select * from table_a  a join table_b b on a.id=b.id;

外连接
左连接：保留左边，用右边表匹配左边表，未匹配到的为null
select * from teacher t left join course c on t.id=c.t_id;
右连接：保留右边，用左边表匹配左边表，未匹配到的为null
select * from teacher t right join course c on t.id=c.t_id;

子查询
where子查询，distinct查询唯一值
select * from student where id in (select distinct s_id from scores where score > 90);

取排名数学成绩前5名的学生
select * from (select s.id,s.name,e.score,c.name cname from student s left join  scores e  on  e.s_id= s.id  left join course c on e.c_id=c.id where c.name='数学' order by e.score desc limit 5) t order by t.score;

练习
查询各个学科的平均成绩，最高成绩
select name cname,avg(score) avg_score from (select c.name,e.score from course c left join scores e on c.id=e.c_id) as total group by name;

查询每个同学的最高成绩及科目名称
select name,max(score) max_score, from(select s.id,s.name,e.score,c.name cname from student s left join  scores e on  e.s_id=s.id  left  join course c  on c.id=e.c_id) as total group by name;

select name,max(score) max_score  from(select s.id,s.name,e.score,e.c_id from student s left join  scores e on  e.s_id=s.id) as total group by name;


事务
事务的四大特点
原子性：要么全部完成，要么全不完成
一致性：事务开始喝结束后，数据库的完整性没有被破坏
隔离性：允许多个事务同时对数据进行读写和修改能力，隔离型可以防止多个事务并发执行由于交叉执行而导致数据的不一致。
持久性：事务结束后，对于数据的修改是永久的

事务提交
都成功
start transaction;
commit;
都失败
start transaction;
rollback;

隔离性
读未提交：read uncommitted    事物A和事物B，事物A未提交的数据，事物B可以读取到  
读已提交：read committed		事物A和事物B，事物A提交的数据，事物B才能读取到
可重复度：repeatable read		事务A和事务B，事务A提交之后的数据，事务B读取不到
串行化：serializable			事务A和事务B，事务A在操作数据库时，事务B只能排队等待

SELECT @@global.tx_isolation, @@tx_isolation;
set session transaction isolation level repeatable read;
SET transaction isolation level read uncommitted;
SET transaction isolation level read committed;
set transaction isolation level repeatable read;
SET transaction isolation level serializable;
SET GLOBAL transaction isolation level read uncommitted;
SET GLOBAL transaction isolation level read committed;
set GLOBAL transaction isolation level repeatable read;
SET GLOBAL transaction isolation level serializable;
其中，SESSION 和 GLOBAL 关键字用来指定修改的事务隔离级别的范围：
SESSION：表示修改的事务隔离级别将应用于当前 session（当前 cmd 窗口）内的所有事务；
GLOBAL：表示修改的事务隔离级别将应用于所有 session（全局）中的所有事务，且当前已经存在的
session 不受影响；
如果省略 SESSION 和 GLOBAL，表示修改的事务隔离级别将应用于当前 session 内的下一个还未开始的
事务