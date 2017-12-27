[TOC]

# BOS物流项目笔记


[BOS物流项目01———项目开发准备](blog/01.md)

[BOS物流项目02———创建项目](blog/02.md)

[BOS物流项目03———模块间的依赖引用](blog/03.md)

[BOS物流项目04———bos-web模块配置之web.xml](blog/04.md)

[BOS物流项目05———bos-web模块配置之struts.xml](blog/05.md)

[BOS物流项目06———bos-web模块配置之log4j.properties](blog/06.md)

[BOS物流项目07———bos-web模块配置之applicationContext.xml](blog/07.md)

[BOS物流项目08———主页设计(easyui)](blog/08.md)

[BOS物流项目09———添加树形结构(zTree)](blog/09.md)

[BOS物流项目10———easyui中messager和menubutton基本使用](blog/10.md)

[BOS物流项目11———登陆和验证登陆的实现](blog/11.md)

[BOS物流项目12———easyui弹框修改密码](blog/12.md)

[BOS物流项目13———基础部分分析](blog/13.md)

[BOS物流项目14———取派员1_添加取派员](blog/14.md)

[BOS物流项目15———取派员2_取派员列表](blog/15.md)

[BOS物流项目16———取派员3_修改取派员](blog/16.md)

[BOS物流项目17———取派员4_批量删除与批量还原](blog/17.md)

[BOS物流项目18———区域数据1_导入功能_一键上传](blog/18.md)

[BOS物流项目19———区域数据2\_导入功能_POI基本使用以及导入区域数据](blog/19.md)

[BOS物流项目20———区域数据3\_导入功能_pinyin4j基本使用以及导入区域数据](blog/20.md)

[BOS物流项目21———区域数据4\_分页查询以及重构分页代码](blog/21.md)

[BOS物流项目22———分区数据1\_添加分区选择区域](blog/22.md)

[BOS物流项目23———分区数据2\_添加分区](blog/23.md)

[BOS物流项目24———分区数据3\_解决区域分页死循环问题](blog/24.md)

[BOS物流项目25———分区数据4\_分页查询没有查询条件](blog/25.md)

[BOS物流项目26———分区数据5\_分页查询带查询条件前端](blog/26.md)

[BOS物流项目27———分区数据6\_分页查询带查询条件后端](blog/27.md)

[BOS物流项目28———分区数据7\_导出分区数据](blog/28.md)

[BOS物流项目29———定区数据1\_添加定区](blog/29.md)

[BOS物流项目30———定区数据2\_定区分页查询以及死循环解决](blog/30.md)

[BOS物流项目31———WebService01_WebService入门](blog/31.md)

[BOS物流项目32———WebService02_发布一个WS服务](blog/32.md)

[BOS物流项目33———WebService03_CXF](blog/33.md)

[BOS物流项目34———WebService04_基于CXF的crm服务](blog/34.md)

[BOS物流项目35———定区数据3\_定区关联客户](blog/35.md)

[BOS物流项目36———定区数据4\_查看定区中包含的分区和客户信息](blog/36.md)

[BOS物流项目37———业务受理1\_自动分单](blog/37.md)

[](blog/38.md)

[](blog/39.md)

[](blog/40.md)

[](blog/41.md)

[](blog/42.md)

[](blog/43.md)

[](blog/44.md)

[](blog/45.md)

[](blog/46.md)

[](blog/47.md)

[](blog/48.md)

[](blog/49.md)

[](blog/50.md)

[](blog/51.md)

[](blog/52.md)

[](blog/53.md)

[](blog/54.md)

[](blog/55.md)

[](blog/56.md)

[](blog/57.md)

[](blog/58.md)

[](blog/59.md)

[](blog/60.md)

[](blog/61.md)

[](blog/62.md)

[](blog/63.md)

[](blog/64.md)

[](blog/65.md)

[](blog/66.md)

[](blog/67.md)

[](blog/68.md)

[](blog/69.md)

[](blog/70.md)

----

[TOC]

# BOS物流项目01———项目开发准备

## 一、项目说明

**BOS**：Bussiness Operating System 业务操作系统


**常见的软件类型**

OA:办公自动化系统

CRM:客户关系管理系统

ERP:综合的企业解决方案（平台）


这个项目只是物流中的一小部分而已。


---

## 二、准备数据库

### 2.1 安装 MySql 数据库

我使用的使用 mac 按照步骤很简单，可以查看下面的文章

[Mac下安装mysql5.7 完整步骤（图文详解）](http://www.jb51.net/article/103841.htm)


### 2.1 创建 bosdb 数据库

数据库使用 MySql5.7,需要创建一个 名为 **bosdb**的数据，添加一个账号为 **xiaoming** 密码为 **123456**的用户 。

1 打开数据库

![](image/01/1.gif)


2 使用 root 账户登录进入

```sql
 mysql -u root -p
```

![](image/01/1.png)

3 创建数据库 **bosdb**

```sql
create database bosdb character set utf8;
```

![](image/01/2.png)

4 创建用户 **xiaoming** 密码是 **123456**

```sql
create user xiaoming identified by '123456' ;
```

![](image/01/3.png)


5 为**xiaoming** 授权
```sql
grant all on bosdb.* to xiaoming;
```

![](image/01/4.png)

6 使用新用户**xiaoming** 登录


![](image/01/5.png)


----

## 三、安装maven

maven 的具体使用可以查看

[Maven学习总结](http://www.cnblogs.com/xdp-gacl/category/544719.html)

### 3.1 maven 下载

下载地址是：

[http://maven.apache.org/download.cgi#](http://maven.apache.org/download.cgi#)

![](image/01/6.png)


### 3.2 解压安装


解压到 **/usr/local/**

例如

```
/Users/myneedtest/apache-maven-3.5.2
```

![](image/01/7.png)

### 3.3 配置maven

在终端，输入

```
vim /.bash_profile
```

在文件中加入下面的代码

```
export M2_HOME=/Users/myneedtest/apache-maven-3.5.2
export PATH=${PATH}:${M2_HOME}/bin
```

保存退出 **wq!**

然后在终端输入 

```
source ~/.bash_profile
maven -version
```

![](image/01/8.png)


### 3.4 配置本地仓库

打开 Maven home 中 conf 中的 settings.xml 配置本地仓库，如下


```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <localRepository>/Users/myneedtest/maven/repository</localRepository>
  ..........
</settings>  
```




----








