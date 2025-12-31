# Notification Telegram - Spring Boot Project

A simple Spring Boot project with Java 17, Spring Web, and Lombok.

## Technologies Used

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Web Starter**
- **Lombok**
- **Maven**

## How to Run

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Build Project

```bash
mvn clean compile
```

### Run Application

```bash
mvn spring-boot:run
```

### Build JAR

```bash
mvn clean package
```

## Endpoints

### Telegram Endpoints

- `POST /api/telegram/send` - Send a simple text message
- `POST /api/telegram/notification` - Send notification with title and content
- `POST /api/telegram/send-html` - Send message with HTML format
- `GET /api/telegram/test` - Test Telegram connection

## Lombok Features

This project uses Lombok to reduce boilerplate code:

- `@Data` - Generates getter, setter, toString, equals, and hashCode
- `@NoArgsConstructor` - Generates constructor without parameters
- `@AllArgsConstructor` - Generates constructor with all parameters

## Default Port

Application runs on port **8080**

## Telegram Configuration

Before using Telegram features, you need to configure the bot token and chat ID in the `src/main/resources/application.properties` file:

```properties
telegram.bot.token=YOUR_BOT_TOKEN_HERE
telegram.bot.chat-id=YOUR_CHAT_ID_HERE
```

### How to Get Bot Token:

1. Chat with [@BotFather](https://t.me/botfather) on Telegram
2. Send the `/newbot` command
3. Follow the instructions to create a new bot
4. Copy the token provided

### How to Get Chat ID:

1. Chat with the bot you created
2. Send any message to the bot
3. Access URL: `https://api.telegram.org/bot<YOUR_BOT_TOKEN>/getUpdates`
4. Find the `chat.id` field in the JSON response

## API Usage Examples

### Send Simple Message

```bash
curl -X POST http://localhost:8080/api/telegram/send \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello from Spring Boot!"}'
```

### Send Notification

```bash
curl -X POST http://localhost:8080/api/telegram/notification \
  -H "Content-Type: application/json" \
  -d '{"title": "Important Notification", "content": "There is a new system update"}'
```

### Test Connection

```bash
curl http://localhost:8080/api/telegram/test
```
