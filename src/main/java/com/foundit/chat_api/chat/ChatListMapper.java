package com.foundit.chat_api.chat;

import com.foundit.chat_api.common.UserRestTemplate;
import com.foundit.chat_api.message.Message;
import com.foundit.chat_api.message.MessageRepository;
import com.foundit.chat_api.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChatListMapper {
    private final UserRestTemplate userRestTemplate;
    private final MessageRepository messageRepository;
    public ChatListMapper(UserRestTemplate restTemplate, MessageRepository messageRepository) {
        userRestTemplate = restTemplate;
        this.messageRepository = messageRepository;
    }

    public ChatListReponse toChatListResponse(Chat chat, int senderId, int itemid, String recieverUsername) {
        User sender = userRestTemplate.getUser(chat.getSender());
        Message message = messageRepository.findLastMessagesByChatId(chat.getId());
        return ChatListReponse.builder()
                .id(chat.getId())
                .itemid(itemid)
                .senderId(chat.getSender())
                .receiverId(chat.getRecipient())
                .receiverUsername(recieverUsername)
                .senderUsername(sender.getUsername())
                .lastMessage(message.getContent())
                .lastMessageTime(message.getCreatedDate())
                .build();
    }
}
