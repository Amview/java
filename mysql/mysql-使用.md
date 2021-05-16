1. mysql导入数据csv，出于安全考虑，默认是不允许从client host远程通过load data命令导数据的
   登录MySql时，需用--local-infile[=1]显式指定参数

```
mysql  --local-infile -u root -p

show global variables like 'local_infile';
//客户端和服务端都要开启local_infile
set global local_infile ='ON';
jdbc:mysql://localhost:3306?allowLoadLocalInfile=true
```
2. 创建表

```
create table site_sale(
id int(20) primary key not null,
siteNum int(20),
sales_amount int(20));
```
delete from site_sale;
delete from sale_range;

3. 导入数据
```
load data local infile "D:/Desktop/***/1.csv"  into table site_sale  fields  terminated by  ","  lines terminated by "\r\n";
```