# Notification Telegram - Spring Boot Project

Project Spring Boot sederhana dengan Java 17, Spring Web, dan Lombok.

## Teknologi yang Digunakan

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Web Starter**
- **Lombok**
- **Maven**


## Cara Menjalankan

### Prerequisites
- Java 17 atau lebih tinggi
- Maven 3.6 atau lebih tinggi

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
- `POST /api/telegram/send` - Mengirim pesan teks sederhana
- `POST /api/telegram/notification` - Mengirim notifikasi dengan judul dan konten
- `POST /api/telegram/send-html` - Mengirim pesan dengan format HTML
- `GET /api/telegram/test` - Test koneksi Telegram

## Fitur Lombok

Project ini menggunakan Lombok untuk mengurangi boilerplate code:
- `@Data` - Generate getter, setter, toString, equals, dan hashCode
- `@NoArgsConstructor` - Generate constructor tanpa parameter
- `@AllArgsConstructor` - Generate constructor dengan semua parameter

## Port Default

Application berjalan di port **8080**

## Konfigurasi Telegram

Sebelum menggunakan fitur Telegram, Anda perlu mengkonfigurasi bot token dan chat ID di file `src/main/resources/application.properties`:

```properties
telegram.bot.token=YOUR_BOT_TOKEN_HERE
telegram.bot.chat-id=YOUR_CHAT_ID_HERE
```

### Cara Mendapatkan Bot Token:
1. Chat dengan [@BotFather](https://t.me/botfather) di Telegram
2. Kirim perintah `/newbot`
3. Ikuti instruksi untuk membuat bot baru
4. Salin token yang diberikan

### Cara Mendapatkan Chat ID:
1. Chat dengan bot yang sudah dibuat
2. Kirim pesan apapun ke bot
3. Akses URL: `https://api.telegram.org/bot<YOUR_BOT_TOKEN>/getUpdates`
4. Cari field `chat.id` dalam response JSON

## Contoh Penggunaan API

### Mengirim Pesan Sederhana
```bash
curl -X POST http://localhost:8080/api/telegram/send \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello dari Spring Boot!"}'
```

### Mengirim Notifikasi
```bash
curl -X POST http://localhost:8080/api/telegram/notification \
  -H "Content-Type: application/json" \
  -d '{"title": "Notifikasi Penting", "content": "Ada update sistem baru"}'
```

### Test Koneksi
```bash
curl http://localhost:8080/api/telegram/test
```
