package com.example.notificationtelegram.dto;

import lombok.Data;

@Data
public class TelegramRequest {
    private String message;
    private String title;
    private String content;
}
