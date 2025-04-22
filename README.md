**前端部分：https://github.com/keeeeq/ai-chat-frontend**


# Chat-AI 聊天应用

## 项目介绍

Chat-AI 聊天应用是一个基于 Spring Boot 和 Spring AI 的智能聊天系统，通过集成 OpenAI 相关技术，实现了一个可爱的猫娘风格 AI 助手。该项目包含完整的后端服务实现，支持多会话管理、上下文记忆等功能。

## 功能特点

- 🤖 **智能聊天**：集成 Spring AI 与 OpenAI 模型，提供智能对话能力
- 💬 **会话管理**：支持多个会话记录并可以随时切换
- 🧠 **上下文记忆**：能够记住对话上下文，提供连贯的对话体验
- 🐱 **猫娘人设**：默认配置为可爱的猫娘风格 AI 助手
- 🔄 **响应式设计**：使用响应式编程模型提供流式响应

## 技术栈

- **后端框架**：Spring Boot 3.4.3
- **AI 框架**：Spring AI 1.0.0-M7
- **数据库**：MySQL
- **构建工具**：Maven
- **Java 版本**：JDK 17
- **其他工具**：Lombok、Reactor(响应式编程)

## 系统架构

```
chat-ai/
├── controller/ - REST API控制器
│   ├── ChatController - 处理聊天请求
│   └── ChatHistoryController - 管理聊天历史
├── repository/ - 数据访问层
│   ├── ChatHistoryRepository - 聊天历史存储接口
│   └── InMemoryChatHistoryRepository - 内存实现
├── config/ - 配置类
│   ├── CommonConfiguration - AI客户端配置
│   └── MvcConfiguration - MVC相关配置
└── HeimaaiApplication.java - 应用入口
```

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 数据库

### 安装步骤

1. **克隆项目**

```bash
git clone https://github.com/yourusername/chat-ai.git
cd chat-ai
```

2. **配置应用**

修改 `src/main/resources/application.yaml` 文件，配置数据库连接和 OpenAI API 密钥：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/chatai
    username: your_username
    password: your_password
  ai:
    openai:
      api-key: your_openai_api_key
      base-url: your_openai_base_url
```

3. **编译运行**

```bash
# 使用Maven包装器
./mvnw spring-boot:run

# 或直接使用Maven
mvn spring-boot:run
```

### 接口使用

- **聊天接口**：`GET /ai/chat?prompt=你的问题&chatId=会话ID`
- **获取历史会话**：`GET /ai/history/{type}`

## 前端项目

前端部分是独立的项目，详见：[ai-chat-frontend](https://github.com/keeeeq/ai-chat-frontend)

## 自定义配置

### 修改 AI 人设

在 `CommonConfiguration.java` 中修改以下代码：

```java
.defaultSystem("你是一个热心可爱的猫娘，你每句话都以'喵'结束")
```

### 更换 AI 模型

在 `application.yaml` 中修改：

```yaml
spring:
  ai:
    openai:
      chat:
        options:
          model: 你想使用的模型名称
```

## 贡献指南

欢迎提交 Pull Request 或 Issue 来完善本项目。

## 许可证

[MIT License](LICENSE)
