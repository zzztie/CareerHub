# CareerHub 项目级学习交接快照

> 快照范围：U01–U22 结束时  
> 快照用途：在新 Chat、长期中断后或进入下一阶段前，恢复 CareerHub 的项目上下文与教学上下文。  
> 资料来源：原“实习学习路线”对话中已确认的学习记录和运行结果。  
> 重要限制：生成本快照时**没有读取 CareerHub 真实项目目录**。凡涉及精确文件内容、路径、字段或最终是否删除临时代码，均应以当前项目文件为准。

---

## 0. 使用本快照前先读这一段

三类资料的优先级如下：

```text
当前真实项目文件 / 实际运行结果
        >
V2 学习计划原文
        >
本交接快照
        >
聊天记忆或推测
```

如果真实代码与本快照冲突，以真实代码为准，并及时更新本文件。

本文用以下标记区分可信度：

- **已确认**：原对话中有代码、运行结果或明确验收结论。
- **待项目核对**：对话能确认设计方向，但生成快照时没有读取真实文件。
- **教学临时内容**：只为理解知识点或连通性而写，不应作为正式业务设计保留。

---

## 1. 用户学习目标与整体路线

### 1.1 核心目标

用户正在通过 CareerHub 完成一条面向 Java 后端实习的项目制学习路线。目标不是只记 API，而是能够：

1. 理解一条后端请求从 Controller 到数据库/缓存再回到客户端的完整数据流。
2. 独立写出可运行、可解释、可调试的 Spring Boot 业务代码。
3. 掌握 Java、Spring Boot、MySQL、MyBatis-Plus、JWT、Redis 等后端主线技术。
4. 同步进行算法“三刷”，保留薄弱点并按单元补题。
5. 用 Git、README、接口文档和统一错误规范，使项目接近可展示、可协作的真实项目。
6. 在 CareerHub v1 基础上继续走向 CareerAI Agent、RAG 和最终求职投递。

### 1.2 当前路线位置

```text
Java / OOP / 集合 / 异常 / Stream
        ↓
CareerHub 内存版分层
        ↓
Git + HTTP + REST
        ↓
Spring Boot + IoC / DI
        ↓
MySQL + MyBatis-Plus
        ↓
参数校验 + 统一响应 + Swagger
        ↓
BCrypt 登录 + JWT
        ↓
拦截器 + UserContext + 受保护接口
        ↓
Redis + 邮箱验证码（当前已完成）
        ↓
补 U22 二叉树 2 题
        ↓
进入 V2 的 U23
```

### 1.3 当前阶段结论

- U01–U21：主线已完成。
- U22：Redis 与邮箱验证码业务已完成并通过运行验收。
- U22 原计划的二叉树 2 题：**待补**。
- 下一正式学习单元：**U23**，但开始前必须重新读取 V2 中 U23 的原文，不能凭快照猜测 U23 内容。

---

## 2. 当前环境和关键版本

| 项目 | 当前已知状态 |
|---|---|
| 操作系统 | Windows 11 |
| Java | JDK 17 |
| Spring Boot | 3.5.5（原对话中已观察到，仍建议以 `pom.xml` 为准） |
| MyBatis-Plus | 3.5.12（原对话中已观察到，仍建议以 `pom.xml` 为准） |
| MySQL | 8.0.34 |
| Redis | 7.0.15 |
| Redis 运行环境 | WSL2 → Ubuntu 24.04 |
| Redis 端口 | 6379 |
| Spring Boot 端口 | 8080 |
| 项目根目录（历史记录） | `D:\projects\CareerHub`，需以当前机器真实目录为准 |
| 基础包名 | `com.zzztie.careerhub` |

### 2.1 Redis 的实际拓扑

```text
Windows 11
├── CareerHub / Spring Boot
└── WSL2
    └── Ubuntu 24.04
        └── Redis Server 7.0.15 :6379
```

已确认：

```text
redis-cli ping
→ PONG
```

Spring Boot 最终使用：

```yaml
spring:
  data:
    redis:
      host: 127.0.0.1
      port: 6379
```

曾使用 `localhost` 出现 `RedisConnectionFailureException`；改为 `127.0.0.1` 并完整重启应用后恢复。

### 2.2 已知配置分层

`application.yml`：

- 应用名 `careerhub`。
- 激活 `dev` profile。

`application-dev.yml`：

- `server.port: 8080`。
- MySQL 数据源配置。
- JWT 配置。
- Redis 主机和端口。

精确键名和层级必须以真实配置文件为准。

---

## 3. CareerHub 当前项目结构和关键类/配置

下面是根据对话还原的**业务结构图**，不是对磁盘文件的逐项扫描结果：

```text
com.zzztie.careerhub
├── controller
│   ├── AuthController
│   ├── UserController
│   ├── JobController
│   └── VerificationCodeController
├── domain
│   ├── BaseEntity
│   ├── User
│   ├── Resume
│   └── Job
├── dto
│   ├── CreateUserRequest
│   ├── UpdateUserRequest
│   ├── UserResponse
│   ├── LoginRequest
│   └── LoginResponse
├── enums
│   └── UserStatus
├── exception
│   ├── GlobalExceptionHandler
│   ├── UserNotFoundException
│   ├── ResumeNotFoundException
│   ├── JobNotFoundException
│   ├── UserValidationException
│   ├── InvalidCredentialsException
│   └── InvalidTokenException
├── mapper
│   └── UserMapper
├── repository
│   ├── ResumeRepository
│   ├── JobRepository
│   └── memory
│       ├── MemoryResumeRepository
│       └── MemoryJobRepository
├── service
│   ├── UserService
│   ├── ResumeService
│   ├── JobService
│   ├── AuthService
│   ├── VerificationCodeService
│   └── impl
│       ├── UserServiceImpl
│       ├── ResumeServiceImpl
│       ├── JobServiceImpl
│       ├── AuthServiceImpl
│       └── VerificationCodeServiceImpl
├── config
│   └── WebConfig
├── interceptor
│   └── AuthInterceptor
├── context
│   └── UserContext
├── security / util（实际包名待核对）
│   └── JwtService
└── CareerHubApplication / Main（实际启动类名待核对）
```

### 3.1 已确认的持久化边界

- `User`：已从内存仓储迁移到 MyBatis-Plus + MySQL。
- `UserServiceImpl`：依赖从旧 `UserRepository` 切换为 `UserMapper`。
- `Resume`、`Job`：截至已知状态仍以 memory repository 为主；若真实项目已继续迁移，以代码为准。
- 旧 `UserRepository` 不应为了修复 `ResumeServiceImpl` 编译错误而重新加回。正确方向是解除 Resume 对旧 UserRepository 的依赖，保持持久化边界清楚。

### 3.2 关键正式配置/资源

```text
pom.xml
application.yml
application-dev.yml
sql/schema.sql
requests.http
README.md
docs/api-error-spec.md
```

### 3.3 正式保留代码 vs 教学临时代码

#### 正式保留

- `VerificationCodeController`
- `VerificationCodeService`
- `VerificationCodeServiceImpl`
- `AuthController` / `AuthService` / `AuthServiceImpl`
- `JwtService`
- `AuthInterceptor`
- `WebConfig`
- `UserContext`
- DTO、统一响应、全局异常处理、Mapper、正式业务 Entity/Service/Controller
- Redis Starter 依赖和 Redis 配置

#### 仅教学临时内容，不属于最终业务设计

- `RedisTestController`
- `RedisTestService`
- 为演示 API 临时创建的测试接口或临时类
- `demo/LambdaDemo`
- 项目内算法练习目录

原对话已要求删除 `RedisTestController.java` 和 `RedisTestService.java`，但没有读取真实目录确认它们现在是否已删除。新 Chat 若接入项目，应先核对并清理。

`demo/` 和 `algorithm/` 曾通过 `.git/info/exclude` 在本机排除，不是正式共享业务代码。注意 `.git/info/exclude` 只在当前仓库本机生效，不会随 Git 推送给其他人。

---

## 4. U01–U22 完成情况与待补事项

> U01–U05 的精确标题和边界未完整保留在本次可读记录中。下表只记录已确认的基础阶段成果；新 Chat 应用 V2 原文校准这五个单元的正式名称，不能把此处概括当成 V2 原文。

| 单元 | 状态 | 已完成内容 / 当前结论 |
|---|---|---|
| U01 | ✅ | 开发环境与学习路线启动；CareerHub 项目开始搭建。精确单元标题待 V2 校准。 |
| U02 | ✅ | Java 基础练习与项目对象入门。精确验收项待 V2 校准。 |
| U03 | ✅ | 面向对象基础在 User 等领域对象中落地。精确验收项待 V2 校准。 |
| U04 | ✅ | 集合、对象协作等基础能力继续落地。精确验收项待 V2 校准。 |
| U05 | ✅ | 接口、分层或集合实践形成后续 Repository/Service 雏形；精确边界待 V2 校准。 |
| U06 | ✅ | 自定义异常；`throw/throws`、Checked/Unchecked；`UserStatus`；`LocalDateTime`；`createTime/updateTime`；栈题 Valid Parentheses；队列题 Recent Counter。 |
| U07 | ✅ | Lambda、函数式接口、Stream 的 `filter/map/sorted/toList/collect`、方法引用、Optional 用途；完成内存用户筛选/排序/映射。 |
| U08 | ✅ | CareerHub v0.1 分层：User/Resume/Job、Repository、MemoryRepository、Service、ServiceImpl、自定义异常；项目骨架形成。 |
| U09 | ✅ | Git 分支、暂存、提交、推送、日志、diff、合并和冲突练习；算法/教学目录与正式代码分离。 |
| U10 | ✅ | HTTP、JSON、REST、请求/响应、状态码；为 Spring Web 接口阶段建立基础。 |
| U11 | ✅ | Spring Boot、IoC/DI、Bean、构造器注入；内存 Repository/Service/Controller 接入 Spring；建立 User/Job Controller。 |
| U12 | ✅ | User REST CRUD；Entity 与 DTO/Response 分离；`CreateUserRequest`、`UpdateUserRequest`、`UserResponse`；使用 `requests.http` 调接口。 |
| U13 | ✅ | MySQL 基础、DDL/DML、主外键、一对多；建立 `user/resume/job` 表与 `sql/schema.sql`。 |
| U14 | ✅ | 索引、事务等 MySQL 进阶概念；联合索引/最左前缀做过教学讨论或实验，是否留在正式 DDL 需核对 schema。 |
| U15 | ✅ | MyBatis-Plus 接入；User 从内存仓储迁移到 `UserMapper + MySQL`；处理旧 UserRepository 依赖冲突。 |
| U16 | ✅ | MyBatis-Plus 条件查询与分页：`LambdaQueryWrapper`、`eq/like/orderByDesc`、`Page/IPage`；User 搜索/分页接口。 |
| U17 | ✅ | Linux/日志/运行排错基础；同时补做算法复习。精确命令清单以 V2 和历史笔记为准。 |
| U18 | ✅ | CareerHub 阶段整合；README 更新到 v0.2；对项目能力与算法进行阶段复盘。 |
| U19 | ✅ | Bean Validation、统一 `ApiResponse<T>`、全局异常处理、错误规范、Swagger/OpenAPI；生成 `docs/api-error-spec.md`。 |
| U20 | ✅ | 注册/登录、BCrypt 密码哈希、JWT 生成；正确登录 200 + token，错误密码/不存在邮箱统一 401。 |
| U21 | ✅ | JWT 解析、`AuthInterceptor`、`WebConfig`、`UserContext(ThreadLocal)`、受保护的 `/user/profile`；401/403 概念。当前未确认角色权限型 403 已实现。 |
| U22 | ✅/⏳ | Redis 主线与邮箱验证码业务已通过；原计划二叉树 2 题待补。 |

### 4.1 唯一明确挂账

```text
U22 Redis / 项目部分：✅
U22 二叉树 2 题：⏳ 待补
```

不能为了“单元看起来完成”把这两题写成已完成。

---

## 5. 当前数据库表和关键字段

### 5.1 `user` 表（已确认主干）

| 字段 | 含义 | 备注 |
|---|---|---|
| `id` | 用户主键 | `BIGINT`，自增主键 |
| `username` | 用户名 | 非空、唯一；精确长度以 schema 为准 |
| `email` | 邮箱 | 非空、唯一；登录标识之一 |
| `password_hash` | BCrypt 密码哈希 | U20 新增；绝不能存明文密码 |
| `status` | 用户状态 | 字符串枚举，如 `ACTIVE` / `DISABLED` |
| `create_time` | 创建时间 | 对应 Java `LocalDateTime` |
| `update_time` | 更新时间 | 对应 Java `LocalDateTime` |

Java 字段和数据库列名的精确映射，以 `User.java`、`UserMapper` 和 `schema.sql` 为准。

### 5.2 `resume` 表（部分已确认）

已确认：

- 有独立主键 `id`。
- 有 `user_id`，与 `user.id` 建立外键或逻辑关联。
- User 与 Resume 是一对多关系。

简历的其余业务字段在当前可读记录中没有足够证据，**不要在新 Chat 中猜字段**。需要查看：

```text
sql/schema.sql
Resume.java
ResumeRepository / MemoryResumeRepository
```

### 5.3 `job` 表（已确认字段）

| 字段 | 含义 |
|---|---|
| `id` | 岗位主键 |
| `title` | 岗位标题 |
| `company` | 公司 |
| `description` | 岗位描述 |
| `create_time` | 创建时间 |
| `update_time` | 更新时间 |

历史设计中常见约束为 `title/company` 非空、`description` 可空；精确长度和约束仍应以 `schema.sql` 为准。

### 5.4 索引与约束

- `username`、`email` 的唯一约束/唯一索引属于正式设计方向。
- Resume → User 的外键关系已在 U13 学习并验证。
- 联合索引和最左前缀在 U14 做过教学，是否正式保留必须查当前 DDL，不能仅凭学习记录判断。

---

## 6. U20–U21 JWT 鉴权链

### 6.1 注册链路

```text
客户端提交注册信息
↓
AuthController
↓
AuthService / AuthServiceImpl
↓
BCrypt.encode(明文密码)
↓
只把 password_hash 写入 MySQL
```

规则：

- 数据库中不得保存明文密码。
- 日志、异常信息、接口响应也不得回显密码或哈希。

### 6.2 登录链路

```text
POST /auth/login
↓
LoginRequest(email, password)
↓
AuthController
↓
AuthServiceImpl
↓
按 email 查询 User
↓
BCrypt.matches(用户输入, password_hash)
├── 失败：InvalidCredentialsException
│          ↓
│        统一 401
└── 成功：JwtService.generateToken(userId)
           ↓
         LoginResponse(token)
```

已验收：

- 正确凭证：HTTP 200，返回 JWT。
- 密码错误：HTTP 401。
- 邮箱不存在：HTTP 401。
- 两种登录失败使用统一提示，避免通过错误信息枚举用户。

### 6.3 受保护接口链路

```text
客户端请求 /user/profile
↓
Authorization: Bearer <JWT>
↓
AuthInterceptor.preHandle(...)
↓
提取 Bearer token
↓
JwtService.parseUserId(token)
├── 校验签名
├── 校验格式
└── 校验过期时间
↓
得到 userId
↓
UserContext.setUserId(userId)
↓
UserController
↓
UserService / UserMapper
↓
MySQL
↓
返回当前用户资料
↓
afterCompletion / finally
↓
UserContext.clear()
```

### 6.4 关键类职责

- `JwtService`：生成 JWT、解析并校验 JWT、从 token 取出 userId。
- `AuthInterceptor`：读取请求头、校验 token、把 userId 放进当前请求上下文。
- `WebConfig`：注册拦截器，配置需要保护和排除的路径。
- `UserContext`：用 `ThreadLocal` 暂存当前请求的 userId。
- `GlobalExceptionHandler`：把无 token、格式错误、签名错误、过期等异常统一映射为 401 响应。

### 6.5 ThreadLocal 安全点

线程池会复用线程，所以请求结束后必须清理：

```text
UserContext.clear();
```

否则上一个请求的 userId 可能污染后续请求。真实代码中应检查 `afterCompletion` 或等价的 `finally` 清理逻辑是否存在。

### 6.6 401 与 403

- 401：未认证或凭证无效，例如缺少、损坏、伪造、过期的 token。
- 403：身份已确认，但没有某项权限。

当前对话明确完成的是 JWT 身份校验与 401 链路；尚未确认角色/权限模型和真正的 403 业务已落地。

---

## 7. U22 Redis 接入与最终邮箱验证码业务

### 7.1 为什么使用 Redis

邮箱验证码具有以下特点：

- 生命周期短。
- 读取频繁且要求快。
- 到期后应自动失效。
- 没有长期保存价值。

因此：

```text
MySQL → 长期、可靠、结构化业务数据
Redis → 高频、临时、带 TTL 的数据
```

### 7.2 正式依赖

项目加入：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

Spring Boot 自动提供 `StringRedisTemplate` Bean，`VerificationCodeServiceImpl` 通过构造器注入使用。

### 7.3 正式业务接口

```java
public interface VerificationCodeService {
    void sendEmailCode(String email);
    boolean verifyEmailCode(String email, String code);
}
```

Service 使用业务语言“发送/验证验证码”，而不是把 `set/get/del` 直接暴露给 Controller。

### 7.4 Redis Key 设计

```text
verification:email:{email}
```

例如：

```text
verification:email:test@qq.com
```

拆解：

- `verification`：验证业务命名空间。
- `email`：验证类型。
- `test@qq.com`：具体邮箱标识。
- Value：六位验证码，例如 `682941`。
- TTL：5 分钟。

代码中使用常量和统一构造函数：

```java
private static final String EMAIL_CODE_KEY_PREFIX = "verification:email:";

private String buildEmailCodeKey(String email) {
    return EMAIL_CODE_KEY_PREFIX + email;
}
```

### 7.5 验证码生成

```java
int number = SECURE_RANDOM.nextInt(1_000_000);
String code = String.format("%06d", number);
```

- 使用 `SecureRandom`，不用普通 `Random`。
- 范围 0–999999。
- `%06d` 保证始终六位，如 `8392` → `008392`。

### 7.6 保存与 TTL

```text
stringRedisTemplate.opsForValue().set(
    key,
    code,
    5,
    TimeUnit.MINUTES
);
```

概念上等价于：

```text
SET verification:email:test@qq.com 682941 EX 300
```

注意：5 分钟是 300 秒，不是 `EX 5`。

TTL 返回值：

- 正数：剩余秒数。
- `-1`：Key 存在，但没有 TTL。
- `-2`：Key 不存在。

### 7.7 验证逻辑

```text
根据 email 构造同一个 Key
↓
GET savedCode
├── null：未发送或已过期 → false
├── 不一致：输入错误 → false，保留 Key
└── 一致：验证成功 → DEL Key → true
```

错误验证码不能立刻删 Key，否则用户一次手误就必须重发。正确验证码必须删除，使其成为一次性凭证，防止同一邮箱的验证码成功后被重放。

### 7.8 Controller 接口与返回

已知接口：

```http
POST /verification/email/send?email=test@qq.com
POST /verification/email/verify?email=test@qq.com&code=682941
```

返回使用统一 `ApiResponse`：

- send：data 类似 `"verification code sent"`。
- verify：data 为 `true` 或 `false`。

精确响应文本以真实 Controller 为准。

### 7.9 已验收行为

```text
发送验证码
→ Redis 出现 Key
→ TTL 约 300 秒

提交错误验证码
→ data=false
→ Key 仍存在

提交正确验证码
→ data=true
→ Key 被删除

一直不使用
→ TTL 到期
→ Key 自动失效
```

### 7.10 当前未实现的部分

- 尚未接入真实邮件发送服务。
- 开发阶段通过 `redis-cli GET verification:email:{email}` 查看验证码。
- 生产环境绝不能提供“读取验证码”的公开 GET 接口，也不能把验证码打到日志或响应中。

### 7.11 Redis 数据结构的正式/教学边界

- 正式验证码业务：使用 Redis String。
- Hash、Set：U22 为理解 Redis 能力而练习。
- `user:15:favorites` Set 等示例是教学示例，不表示收藏岗位业务已正式实现。

---

## 8. 当前算法三刷状态

### 8.1 状态来源与限制

算法进度曾单独记录在：

```text
D:\study\algorithm-progress.md
```

该文件不在本次工作区，也没有被实际读取。因此下面是对话中可确认的最近状态，不应覆盖真实进度表。

### 8.2 已知进度

| 题目/类型 | 对话中可确认状态 | 已知薄弱点或备注 |
|---|---|---|
| Two Sum / HashMap | 至少完成前两刷；U20 又有一次练习记录 | 容易把返回值写成值而不是下标；是否已正式标记三刷完成需查进度表 |
| Valid Anagram / HashMap | 至少完成前两刷，U17 有复习记录 | 基本稳定；正式三刷标记待核对 |
| Contains Duplicate / HashSet | 至少完成前两刷 | 基本稳定 |
| Intersection of Two Arrays / HashSet | 至少完成前两刷 | `Set` 转 `int[]` 还不熟 |
| Ransom Note / HashMap | 至少完成前两刷 | 基本稳定 |
| Valid Parentheses / Stack | U06 完成，后续有复习 | 理解“最后进入的左括号必须最先匹配” |
| Recent Counter / Queue | U06 完成，U19 附近有复习 | 理解窗口外元素从队头淘汰 |
| Reverse Linked List | 已做，U21 再次练习 | 具体刷次以进度表为准 |
| Middle of the Linked List | U21 完成 | 快慢指针 |
| Binary Search / Search Insert 等 | U10 附近完成二分专题练习 | 精确题目和刷次以进度表为准 |
| Maximum Depth / 二叉树基础 | 早期单元已有练习 | U22 原计划的二叉树 2 题仍待补 |
| Move Zeroes / 双指针 | 已完成并在阶段验收中出现 | 具体刷次以进度表为准 |
| Longest Common Prefix | U20 附近有练习记录 | 是否计入正式三刷表待核对 |

### 8.3 三刷管理规则

1. 不因“看过答案”标记完成。
2. 每一刷记录日期、是否独立完成、复杂度、错误点。
3. 对话记录和真实 `algorithm-progress.md` 冲突时，以真实进度文件为准。
4. U22 二叉树 2 题必须继续挂账，直到真实完成并更新进度表。

---

## 9. 重要安全注意事项

### 9.1 永远不要泄露的内容

- `DB_PASSWORD`
- `JWT_SECRET`
- 数据库连接中的真实密码
- 完整可用的 JWT
- 邮箱验证码
- 任何 API Key、OAuth Secret 或生产环境凭证

### 9.2 配置规则

- `DB_PASSWORD` 和 `JWT_SECRET` 通过环境变量或 IDE Run Configuration 注入。
- `application-dev.yml` 只能引用环境变量，不能写真实秘密。
- 不把 `.env`、运行配置、密码或 secret 提交到 Git。
- 示例值使用占位符，例如 `${DB_PASSWORD}`、`${JWT_SECRET}`。
- 截图、日志、错误堆栈和 Chat 消息中也要检查是否含秘密。

### 9.3 JWT 规则

- `JWT_SECRET` 是服务器端签名密钥，不是客户端 token。
- 客户端拿到的是 JWT，不应拿到签名 secret。
- 登录失败提示应统一，避免暴露“邮箱存在但密码错误”之类的差异。
- 401 响应不要回显 token 解析细节、密钥信息或内部堆栈。

### 9.4 Redis 验证码规则

- 不提供读取验证码的公开接口。
- 不把验证码写入生产日志。
- 验证成功立即删除，防止重放。
- 当前逻辑允许错误后继续输入；后续生产化时还应考虑发送频率限制、尝试次数限制、邮箱规范化和防刷。

### 9.5 密码规则

- 只保存 BCrypt 哈希。
- 不记录明文密码，不通过响应返回密码哈希。
- 调试数据库时也不要把真实用户密码或 hash 粘贴到 Chat。

---

## 10. 后续教学规则和用户偏好

1. **每次进入新的 Uxx，先读取 V2 对应单元。** 不凭记忆直接往下讲。
2. **以真实项目当前状态为核心。** 快照只负责恢复历史，代码负责确定现在。
3. **先解释为什么，再写代码。** 先说明业务目标、数据流和每层职责。
4. **一次只引入少量新概念。** 不把多个框架知识点一次性堆给用户。
5. **让用户自己写、自己跑、自己解释。** 不默认整段复制粘贴就是掌握。
6. **分步验收。** 每完成一个小链路，用真实请求、数据库/Redis 状态和口头解释确认。
7. **及时纠正表述。** 例如 `EX 300`、一次性验证码防的是同一凭证重放，而不是“另一个邮箱碰巧拿到相同数字”。
8. **正式业务文件与教学脚手架分离。** API 演示优先放在聊天中；只有确认是最终业务设计后，才在真实项目中创建文件。
9. **临时代码及时清理。** 不让 `RedisTest*`、演示 Controller、算法类混入生产业务结构。
10. **保留未完成事项。** 用户累时可以暂停算法，但必须明确挂账，不能虚假结项。
11. **使用构造器注入。** 继续保持 Spring Bean 职责清晰、依赖显式。
12. **安全信息不进入 Chat、Git、日志或截图。** 遇到配置问题时只核对变量名和是否加载，不要求用户粘贴真实值。
13. **用户偏好中文、业务化讲解和流程图式说明。** 对抽象概念优先用 CareerHub 当前链路解释。

---

## 11. 下一单元入口

### 11.1 推荐顺序

```text
第一步：核对 CareerHub 真实项目
↓
确认 RedisTest* 是否已删除
确认验证码正式代码仍可运行
确认 UserContext 必定清理
确认 schema 与本快照字段一致

第二步：补 U22 二叉树 2 题
↓
更新 algorithm-progress.md

第三步：读取 V2 的 U23 原文
↓
开始 U23
```

### 11.2 U23 的启动口令

用户可以直接发：

```text
U23
```

新的 Chat 应执行：

```text
读取 V2 的 U23
↓
读取 learning-handoff.md
↓
检查 U22 二叉树挂账
↓
读取与 U23 有关的真实项目文件
↓
根据当前代码开始教学
```

本快照没有可靠记录 U23 的具体主题，因此不得自行编造 U23 教学内容。

---

## 12. 新 Chat 使用说明

### 12.1 建议保存位置

把本文件复制到 CareerHub 项目中：

```text
CareerHub/docs/learning-handoff.md
```

本次生成的交付文件可以先下载/复制，再放入上述位置。

### 12.2 新开 Chat 时怎么引用

新 Chat 的第一条消息可以这样发：

```text
继续我的 CareerHub 实习学习计划。

我提供了 learning-handoff.md，请先完整读取它，并继续遵守其中的教学规则。
同时读取我的 V2 学习计划。

当前正式进入 U23；但 U22 还有二叉树 2 题挂账。
每次进入新的 Uxx，都先读取 V2 对应单元，再结合交接快照和当前真实代码教学。

如果交接快照与真实项目文件冲突，以真实项目文件为准。
不要一次塞太多新概念，先解释为什么和完整业务流，再一步一步实现并验收。
```

然后上传或引用：

- `learning-handoff.md`
- `CareerAI_实习学习计划_V2_学习单元制.docx`（或其当前版本）

### 12.3 以后如何发送 Uxx

上下文文件已提供后，只需发送：

```text
U23
```

或：

```text
继续 U23，从上次未完成的验收项开始。
```

不要只说“继续”而不带单元编号，尤其在跨 Chat 或隔很久之后。

### 12.4 什么时候必须提供真实项目文件

遇到以下问题，不能只依赖快照：

- “`User.java` 现在到底有哪些字段？”
- “`WebConfig` 目前拦截了哪些路径？”
- “`VerificationCodeServiceImpl` 当前代码有没有问题？”
- “为什么项目编译失败/启动失败/接口 500？”
- “数据库字段与 Entity 是否一致？”
- “某个临时类到底删没删？”
- “依赖版本到底是多少？”

此时应提供或允许读取相应真实文件，优先范围通常是：

```text
pom.xml
application.yml
application-dev.yml（先移除真实秘密）
sql/schema.sql
相关 Controller / Service / Impl
相关 Entity / DTO / Mapper
WebConfig / AuthInterceptor / UserContext / JwtService
完整错误堆栈（先脱敏）
```

### 12.5 不要上传或粘贴什么

- 真实 `DB_PASSWORD`
- 真实 `JWT_SECRET`
- 可用的生产 JWT
- 真实验证码
- 含秘密的 `.env` 或 Run Configuration 截图

可以只提供变量名、已脱敏配置和错误信息。

### 12.6 何时更新本快照

建议至少在这些节点更新：

```text
U22 Redis 阶段结束（本次）
U35 / CareerHub v1 完成
CareerAI Agent 第一版完成
RAG 阶段完成
正式投递前
```

每次更新时至少补充：

- 新增/删除的正式类。
- 数据库迁移和字段变化。
- 新接口、鉴权范围和错误码。
- 新的环境依赖和版本。
- 算法挂账与三刷状态。
- 仍存在的技术债和安全风险。

---

## 13. 新 Chat 的第一轮核对清单

为了让后续教学真正建立在当前代码上，新 Chat 读取快照后应先确认：

- [ ] 当前 CareerHub 项目目录和 Git 分支。
- [ ] `pom.xml` 中 Spring Boot、MyBatis-Plus、Redis、JWT 依赖版本。
- [ ] `application-dev.yml` 只引用环境变量，没有真实秘密。
- [ ] `user/resume/job` 的真实 DDL。
- [ ] `User.java` 是否与数据库字段一致。
- [ ] `WebConfig` 的拦截与排除路径。
- [ ] `UserContext` 是否在请求结束后清理。
- [ ] `RedisTestController/Service` 是否已删除。
- [ ] `/verification/email/send` 与 `/verify` 是否仍能跑通。
- [ ] U22 二叉树 2 题是否仍未完成。
- [ ] `algorithm-progress.md` 的真实三刷状态。

完成这些核对后，再把本快照中的“待项目核对”内容改成“已确认”。

---

## 14. 一句话恢复当前状态

```text
CareerHub 已完成 User 的 MySQL/MyBatis-Plus 持久化、统一校验与异常响应、BCrypt 登录与 JWT、拦截器鉴权和 UserContext，并已用 StringRedisTemplate 实现 5 分钟一次性邮箱验证码；U22 Redis 主线通过，真实邮件发送尚未接入，U22 二叉树 2 题待补，下一正式单元为 U23。
```
