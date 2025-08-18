package com.example.notificationtelegram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class TelegramService {

    private final RestTemplate restTemplate;
    
    @Value("${telegram.bot.token}")
    private String botToken;
    
    @Value("${telegram.bot.chat-id}")
    private String chatId;
    
    private static final String TELEGRAM_API_URL = "https://api.telegram.org/bot{token}/sendMessage";

    public TelegramService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean sendMessage(String message) {
        try {
            String url = TELEGRAM_API_URL.replace("{token}", botToken);
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("chat_id", chatId);
            requestBody.put("text", message);
            requestBody.put("parse_mode", "HTML");
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            log.info("Mengirim pesan ke Telegram: {}", message);
            
            restTemplate.postForObject(url, request, String.class);
            
            log.info("Pesan berhasil dikirim ke Telegram");
            return true;
            
        } catch (Exception e) {
            log.error("Gagal mengirim pesan ke Telegram: {}", e.getMessage(), e);
            return false;
        }
    }

    public boolean sendHtmlMessage(String message) {
        return sendMessage(message);
    }

    public boolean sendNotification(String title, String content) {
        String formattedMessage = String.format("<b>%s</b>\n\n%s", title, content);
        return sendMessage(formattedMessage);
    }
}
