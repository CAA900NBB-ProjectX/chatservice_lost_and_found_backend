package com.foundit.chat_api.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatDto {
    private String token;
    private String receiverId;
    private int itemId;
}
