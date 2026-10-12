# CareerHub

CareerHub is a Java backend project for career, resume, and job management.

The project is developed step by step as a practical backend project.  
The current version focuses on completing the basic backend workflow of the **User module**, including REST APIs, MySQL persistence, conditional queries, and pagination.

---

## Current Version

**CareerHub v0.2**

Current milestone:

- Spring Boot backend structure
- User REST API
- MySQL persistence
- MyBatis-Plus integration
- Conditional query
- Pagination
- DTO-based request and response separation
- Basic exception handling
- Basic database and Linux practice

The current User module can be demonstrated through a complete request chain:

```text
HTTP Client
    ↓
Controller
    ↓
Service
    ↓
Mapper
    ↓
MyBatis-Plus
    ↓
MySQL
```

---

## Tech Stack

- Java 17
- Spring Boot 3.5.5
- Maven
- MySQL 8
- MyBatis-Plus 3.5.12
- REST API
- Git

---

## Current Features

### User Module

The User module currently supports:

- Create user
- Get user by ID
- Get all users
- Update user
- Delete user
- Search users by username keyword
- Filter users by status
- Paginated user query
- Combined query conditions

User data is persisted in MySQL through MyBatis-Plus.

---

## Project Structure

```text
com.zzztie.careerhub
├── config
├── controller
├── domain
├── dto
├── enums
├── exception
├── mapper
├── repository
│   └── memory
├── service
│   └── impl
└── CareerHubApplication
```

### Package Responsibilities

```text
controller
→ Receives HTTP requests and returns HTTP responses

service
→ Handles business logic

service.impl
→ Service implementations

mapper
→ Database access through MyBatis-Plus

domain
→ Domain entities

dto
→ Request and response objects

enums
→ Business enums

exception
→ Custom exceptions

config
→ Spring Boot / MyBatis-Plus configuration

repository.memory
→ Temporary in-memory implementations for unfinished modules
```

---

## User Request Flow

A typical User request follows this flow:

```text
HTTP Request
    ↓
UserController
    ↓
UserService
    ↓
UserServiceImpl
    ↓
UserMapper
    ↓
MyBatis-Plus
    ↓
MySQL
```

The query result then returns through:

```text
MySQL
    ↓
User Entity
    ↓
Service
    ↓
Controller
    ↓
UserResponse
    ↓
JSON Response
```

---

## DTO Design

The User module currently uses DTOs to separate the HTTP API from the internal domain model.

### CreateUserRequest

Used when creating a user.

```text
POST /users
        ↓
CreateUserRequest
        ↓
User
```

### UpdateUserRequest

Used when updating a user.

```text
PUT /users/{id}
        ↓
UpdateUserRequest
        ↓
User
```

### UserResponse

Used when returning User data to the client.

```text
User Entity
    ↓
UserResponse
    ↓
HTTP JSON Response
```

This prevents the API layer from directly exposing the complete internal `User` entity.

---

## User API

### Create User

```http
POST /users
```

Example request:

```json
{
  "username": "Jerry",
  "email": "jerry@example.com"
}
```

---

### Get User by ID

```http
GET /users/{id}
```

Example:

```http
GET /users/8
```

---

### Get All Users

```http
GET /users
```

---

### Update User

```http
PUT /users/{id}
```

Example request:

```json
{
  "username": "JerryUpdated",
  "email": "jerry_updated@example.com"
}
```

---

### Delete User

```http
DELETE /users/{id}
```

---

## User Search

The project supports dynamic query conditions through MyBatis-Plus `LambdaQueryWrapper`.

Example:

```http
GET /users/search?keyword=e
```

Search by user status:

```http
GET /users/search?userStatus=ACTIVE
```

Combined query:

```http
GET /users/search?keyword=e&userStatus=ACTIVE
```

Internally, query conditions are dynamically constructed according to the parameters provided by the client.

Example logic:

```text
keyword exists
→ username LIKE '%keyword%'

userStatus exists
→ status = userStatus

both exist
→ combine the two conditions
```

---

## Pagination

The User module supports paginated queries.

Example:

```http
GET /users/page?pageNum=1&pageSize=3
```

Combined with keyword search:

```http
GET /users/page?keyword=e&pageNum=1&pageSize=3
```

Combined with status filtering:

```http
GET /users/page?userStatus=ACTIVE&pageNum=1&pageSize=3
```

Pagination currently returns information such as:

```text
current
size
total
records
```

The records returned by the API are converted from `User` entities to `UserResponse`.

---

## Database

Current database:

```text
careerhub
```

Current main tables:

```text
user
resume
job
```

### Current Persistence Status

```text
User
→ MyBatis-Plus
→ MySQL
```

The User module has completed database persistence.

The Resume and Job modules are still under development and currently retain temporary in-memory repository implementations.

They will be migrated to persistent storage in later versions.

---

## Database Relationships

Current basic relationship:

```text
User 1 : N Resume
```

The `resume.user_id` field references the corresponding User.

Example:

```text
user.id = resume.user_id
```

Basic `LEFT JOIN` and `INNER JOIN` queries have also been practiced for multi-table querying.

---

## Query and Database Capabilities

Current database-related capabilities include:

- SELECT
- INSERT
- UPDATE
- DELETE
- WHERE
- LIKE
- ORDER BY
- LIMIT
- Pagination
- LEFT JOIN
- INNER JOIN
- Unique constraints
- Basic indexes
- Basic transaction concepts
- MyBatis-Plus CRUD
- LambdaQueryWrapper

---

## Local Development

### Requirements

- JDK 17
- Maven
- MySQL 8

### Database

Create a MySQL database:

```sql
CREATE DATABASE careerhub;
```

Database schema files are stored in:

```text
sql/
```

Configure the local datasource before starting the project.

Do not commit real database passwords or other secrets to the public repository.

---

## Run the Application

The main Spring Boot entry point is:

```text
CareerHubApplication
```

The application can be started from IntelliJ IDEA or Maven.

After startup, the API is available locally, for example:

```text
http://localhost:8080
```

---

## Current Architecture

```text
                HTTP Request
                     ↓
              UserController
                     ↓
               UserService
                     ↓
            UserServiceImpl
                     ↓
                UserMapper
                     ↓
              MyBatis-Plus
                     ↓
                   MySQL
                     ↓
              User Entity
                     ↓
              UserResponse
                     ↓
                JSON Response
```

---

## Development Status

### Completed

- Java backend project structure
- Git workflow
- Spring Boot integration
- REST API basics
- Controller / Service / DTO separation
- MySQL table design
- User persistence
- MyBatis-Plus integration
- User CRUD
- Conditional queries
- Pagination
- Basic JOIN queries
- Basic database indexes and transactions
- Basic Linux commands and log inspection

### In Progress / Planned

- Parameter validation
- Global exception handling
- Unified API response
- Swagger / OpenAPI
- Password security
- JWT authentication
- Request authentication
- Redis integration
- Cache strategy
- Resume file upload and management
- Job module persistence
- Job search
- Automated testing
- Index optimization
- Performance baseline
- Docker deployment
- AI integration
- CareerAI integration

---

## Roadmap

### CareerHub v0.2

Current version.

Main goal:

```text
Spring Boot
+ MySQL
+ MyBatis-Plus
+ User REST API
+ Pagination
```

### CareerHub v0.5

Planned:

- JWT authentication
- Redis
- Resume module
- Job module
- Global exception handling
- OpenAPI documentation

### CareerHub v1.0

Planned:

- Automated testing
- Database optimization
- Performance baseline
- Complete README and architecture documentation
- Deployable project version

### CareerAI

CareerAI will be developed after the CareerHub backend foundation is stable.

Planned capabilities include:

- LLM integration
- Resume analysis
- Job matching
- RAG
- Tool Calling
- Agent workflow

CareerAI will later interact with CareerHub through backend APIs.

---

## Project Goal

The goal of CareerHub is not only to implement features, but also to build a backend project whose architecture and technical decisions can be clearly explained.

Current development principle:

> Build a complete, stable, and explainable Java backend foundation before adding AI capabilities.

---

## Status

**Active development**

Current milestone:

```text
CareerHub v0.2
User module end-to-end workflow completed
```

## 自动化测试

### 测试技术

项目使用 JUnit 5、Mockito 和 Maven Surefire 进行 Service 层单元测试。

### 测试执行

在项目根目录运行：

`mvn test`

当前测试结果：

- Tests run: 20
- Failures: 0
- Errors: 0
- Skipped: 0
- BUILD SUCCESS

### 核心测试场景

| 模块 | 测试内容 |
|---|---|
| JobService | 岗位查询、更新和删除；Redis 缓存命中、未命中、损坏数据回源及缓存失效重试 |
| UserService | 用户创建、查询、更新、删除及用户不存在的异常处理 |
| AuthService | 用户注册、登录成功生成 JWT、密码错误时拒绝登录并禁止生成 JWT |

### 测试策略

1. 使用 Mockito 模拟 Mapper、Redis、密码编码器等外部依赖，独立验证 Service 业务逻辑。
2. 使用 JUnit 断言验证返回结果和异常。
3. 使用 Mockito `verify()` 验证关键依赖调用次数和执行行为。
4. 同时覆盖正常路径与重要异常路径，尤其关注数据不存在、缓存故障和认证失败。

### 当前测试范围说明

当前 21 个测试主要为 Service 层单元测试，不能替代真实数据库、Redis 或 HTTP 接口的集成测试。

文件上传相关业务、Controller 接口及完整认证流程仍需要进一步测试，后续可继续补充。

### ResumeService 文件上传边界测试

新增 `uploadResume_whenFileEmpty_shouldThrowAndNotSave` 测试用例。

该测试使用 Mockito 模拟用户查询和当前登录用户身份，验证：

- 当前用户具有对应简历操作权限。
- 上传文件为空时，抛出 `IllegalArgumentException`。
- 不调用 `MultipartFile.transferTo()` 写入磁盘。
- 不调用 `ResumeMapper` 操作简历数据库。

**当前未覆盖：** 真实 PDF 上传、文件大小限制、文件类型校验的其他分支、磁盘写入失败及文件删除时的数据库与磁盘一致性处理。

### U34｜岗位数量统计接口性能优化

**优化目标：** 减少岗位数量统计接口不必要的数据读取与对象映射。

**原始实现：** `JobService.getAllJobs().size()`，底层使用 MyBatis-Plus `selectList(null)` 查询全部岗位记录。

**优化实现：** 新增 `JobService.countJobs()`，底层使用 `selectCount(null)` 执行数据库计数查询，保留原有 `getAllJobs()` 方法。

**测试环境与方式：**
- 本机 Spring Boot + MySQL 环境
- 测试接口：`GET /jobs/count`
- 岗位数据：5 条
- 每组预热 5 次，正式顺序请求 30 次
- PowerShell `Stopwatch` 统计客户端 HTTP 响应时间

| 指标 | 优化前 | 优化后 |
|---|---:|---:|
| 平均响应时间 | 2.69 ms | 2.00 ms |
| P95 响应时间 | 4.66 ms | 2.48 ms |

**测试结果：** 当前环境下平均响应时间降低约 25.7%，P95 延迟降低约 46.8%。

**结论：** 采用数据库计数查询，避免为统计记录数量而加载完整岗位对象。当前性能数据来自小数据量本地实验，不代表生产环境或高
并发条件下的改善幅度。

## 性能测试与优化

### 1. Redis 岗位详情缓存性能对照

针对 `GET /jobs/{id}` 接口，对 Redis Cache Aside 的缓存命中和未命中路径进行了 10 轮成对测量。

| 场景 | 平均 HTTP 响应时间 |
|---|---:|
| Cache MISS | 5.41 ms |
| Cache HIT | 3.35 ms |

在本地实验环境中，缓存命中路径的平均响应延迟比未命中路径降低约 38.1%。

通过分段计时进一步分析发现，预热后 MyBatis `selectById()` 的平均调用耗时约 2.13 ms；首次数据库调用存在明显的额外开销，因此未直接将首次高耗时判定为慢 SQL。

### 2. 岗位数量统计接口优化

**优化前：**

`GET /jobs/count` 通过 `getAllJobs().size()` 获取数量，底层执行 `selectList(null)`，需要加载所有岗位记录。

**优化后：**

新增 `JobService.countJobs()`，通过 MyBatis-Plus `selectCount(null)` 在数据库中直接统计岗位数量，避免不必要的数据读取与对象映射。

| 性能指标 | 优化前 | 优化后 |
|---|---:|---:|
| 平均响应时间 | 2.69 ms | 2.00 ms |
| P95 响应时间 | 4.66 ms | 2.48 ms |

**实验结论：**

在当前本地环境中，平均响应延迟降低约 25.7%，P95 延迟降低约 46.8%。

**测试条件与限制：**

- Spring Boot + MySQL + Redis，本机 HTTP 顺序请求
- 岗位数据量：5 条
- 优化前后各预热 5 次、正式测量 30 次
- 使用 PowerShell Stopwatch 记录客户端请求耗时
- 数据量较小，测试结果不能直接推广到生产环境或高并发场景

### 3. 自动化回归测试

使用 JUnit 5、Mockito 和 Maven 对核心 Service 业务进行回归测试。

`mvn test`

当前 21 个自动化测试已全部通过。