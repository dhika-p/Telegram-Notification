package com.example.notificationtelegram.controller;

import com.example.notificationtelegram.dto.TelegramRequest;
import com.example.notificationtelegram.dto.TelegramResponse;
import com.example.notificationtelegram.service.TelegramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
@Slf4j
public class TelegramController {

    private final TelegramService telegramService;

    /**
     * Mengirim pesan teks sederhana
     */
    @PostMapping("/send")
    public ResponseEntity<TelegramResponse> sendMessage(@RequestBody TelegramRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new TelegramResponse(false, "Pesan tidak boleh kosong"));
        }

        boolean isSuccess = telegramService.sendMessage(request.getMessage());
        String message = isSuccess ? "Pesan berhasil dikirim" : "Gagal mengirim pesan";
        
        return ResponseEntity.ok(new TelegramResponse(isSuccess, message));
    }

    /**
     * Mengirim notifikasi dengan judul dan konten
     */
    @PostMapping("/notification")
    public ResponseEntity<TelegramResponse> sendNotification(@RequestBody TelegramRequest request) {
        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new TelegramResponse(false, "Judul tidak boleh kosong"));
        }
        
        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new TelegramResponse(false, "Konten tidak boleh kosong"));
        }

        boolean isSuccess = telegramService.sendNotification(request.getTitle(), request.getContent());
        String message = isSuccess ? "Notifikasi berhasil dikirim" : "Gagal mengirim notifikasi";
        
        return ResponseEntity.ok(new TelegramResponse(isSuccess, message));
    }

    /**
     * Mengirim pesan HTML
     */
    @PostMapping("/send-html")
    public ResponseEntity<TelegramResponse> sendHtmlMessage(@RequestBody TelegramRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new TelegramResponse(false, "Pesan HTML tidak boleh kosong"));
        }

        boolean isSuccess = telegramService.sendHtmlMessage(request.getMessage());
        String message = isSuccess ? "Pesan HTML berhasil dikirim" : "Gagal mengirim pesan HTML";
        
        return ResponseEntity.ok(new TelegramResponse(isSuccess, message));
    }

    /**
     * Test endpoint untuk memeriksa koneksi
     */
    @GetMapping("/test")
    public ResponseEntity<TelegramResponse> testConnection() {
        boolean isSuccess = telegramService.sendMessage("🧪 Test koneksi dari Spring Boot Application");
        String message = isSuccess ? "Test koneksi berhasil" : "Test koneksi gagal";
        
        return ResponseEntity.ok(new TelegramResponse(isSuccess, message));
    }
}
