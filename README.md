# 学生成绩管理系统

## 项目简介

基于 Spring Boot + MyBatis + MySQL 的学生成绩管理后端接口，
支持学生信息的增删改查、按姓名搜索和分页查询。

## 技术栈

- Java 17+
- Spring Boot 4.1.1
- MyBatis
- MySQL 8.x
- Maven
- Postman

> Java 版本请根据 pom.xml 中的实际配置修改。




## 功能列表
- 查询学生列表
- 根据 id 查询学生
- 新增学生
- 修改学生
- 删除学生
- 按姓名搜索
- 分页查询

## 数据库准备
```
本项目使用数据库：
db01
表结构：
CREATE TABLE student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    score INT NOT NULL
);
数据库初始化脚本位置：
sql/schema.sql
sql/data.sql
```
## 配置说明
```text
项目使用环境变量读取数据库账号和密码：
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
运行时需要设置：
$env:DB_USERNAME="root"
$env:DB_PASSWORD="你的数据库密码"
```

## 本地运行
```
1. 创建数据库
在 MySQL 中执行：
CREATE DATABASE IF NOT EXISTS db01
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
2. 创建表并插入测试数据
执行：
sql/schema.sql
sql/data.sql
3. 启动项目
Windows PowerShell：
.\mvnw.cmd spring-boot:run
4. 访问接口
GET http://localhost:8080/students
```
## API 接口
``````
方法	地址	功能
GET	/students	查询学生，支持搜索和分页
GET	/students/{id}	根据 id 查询学生
POST	/students	新增学生
PUT	/students/{id}	修改学生
DELETE	/students/{id}	删除学生
``````


## 请求示例
````
分页查询
GET /students?name=张&page=1&size=10
新增学生
POST /students
Content-Type: application/json
{
  "name": "张三",
  "score": 95
}
````
## 响应格式
``````
成功响应：
{
  "code": 200,
  "message": "success",
  "data": {}
}
错误响应：
{
  "code": 404,
  "message": "学生不存在: id=999",
  "data": null
}
``````

## 已知限制
- 当前版本主要提供后端接口
- 前端页面暂未实现
- 登录和权限功能暂未实现

##   后续计划
- 增加前端页面
- 增加成绩统计分析
- 增加登录鉴权
- 完善单元测试

## 系统架构

```text
HTTP Request
    ↓
Controller
    ↓
Service
    ↓
StudentMapper
    ↓
MyBatis
    ↓
MySQL