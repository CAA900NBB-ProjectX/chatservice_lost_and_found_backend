package com.foundit.chat_api.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {

    private String id;
    private int itemid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int senderId;
    private int receiverId;
}
