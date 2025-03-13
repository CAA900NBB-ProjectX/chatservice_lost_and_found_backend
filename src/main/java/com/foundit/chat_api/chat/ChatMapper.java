package com.foundit.chat_api.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat chat, String senderId, int itemid) {
        return ChatResponse.builder()
                .id(chat.getId())
                .itemid(itemid)
                .lastMessage(chat.getLastMessage())
                .lastMessageTime(chat.getLastMessageTime())
                .senderId(chat.getSender().getId().toString())
                .receiverId(chat.getRecipient().getId().toString())
                .build();
    }
}
