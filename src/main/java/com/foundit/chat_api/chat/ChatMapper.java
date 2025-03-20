package com.foundit.chat_api.chat;

import org.springframework.stereotype.Service;

@Service
public class ChatMapper {
    public ChatResponse toChatResponse(Chat chat, int senderId, int itemid) {
        return ChatResponse.builder()
                .id(chat.getId())
                .itemid(itemid)
                .senderId(chat.getSender())
                .receiverId(chat.getRecipient())
                .build();
    }
}
