package com.foundit.chat_api.chat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatListReponse {
    private String id;
    private int itemid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private int senderId;
    private int receiverId;
    private String senderUsername;
    private String receiverUsername;
}
