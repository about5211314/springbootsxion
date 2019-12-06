# Divinegon
Divinegon的目标是借助springboot的开发框架与activiti流程引擎搭建一套基础应用平台，包括：组织、用户、角色、权限、菜单、数据字典管理、自定义表单以及工作流特性

# 前端项目

## 浏览器：
- (待定)

## 客户端：
- (待定)

本项目计划采用：
>框架：SpringBoot 2.2.1

##springboot-core集成
>持久层框架中：MyBatisPlus 3.1.2 

>数据库：MySQL 5.7

>认证与权限：shiro 1.4.0

##springboot-activiti集成
>流程：activiti 6.0 和 在线流程编辑器

>自定义表单：vue-formbuilder
 
>api文档自动生成：showdoc 


# 发展状况

## 一期
>2019.9.10   项目启动

>2019.9.15   关联showdoc               已完成 

>2019.9.20   集成SpringBoot            已完成 
   
>2019.9.20   集成MyBatisPlus、MySQL     已完成

     >>通过CodeGenerator类，自动生成entity、mapper、service
     >>规则：输入模块名称，然后输入表名，自动生成在com.core.springbootcore下

>2019.11.12  集成shiro                 已完成

     >>密码通过md5两次加密验证
     
     >>通过权限表进行控制

>2019.11.10  集成activiti              已完成

     >>core集成springboot-activiti.jar

>2019.12.x   集成vue-formbuilder       设计中

## 二期
>组织管理 待启动

>用户管理 待启动

>角色管理 待启动

>权限管理 待启动

>菜单管理 待启动

>数据字典管理 待启动

>自定义表单管理 待启动

>工作流管理 待启动




# Divinegon的特点

>暂无

# Divinegon功能介绍

>暂无

# 体验地址

>暂无

# 项目部署

>1、安装开发环境 idea  + jdk1.8 + mysql5.7
    