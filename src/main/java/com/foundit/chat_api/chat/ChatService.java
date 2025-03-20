package com.foundit.chat_api.chat;


import com.foundit.chat_api.common.TokenResolver;
import com.foundit.chat_api.common.UserRestTemplate;
import com.foundit.chat_api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRestTemplate userRestTemplate;
    private final TokenResolver tokenResolver;
    private final ChatMapper mapper;

    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByLoginUserForItem(String token, String ItemPostedUserId, int itemId) {
        final int userId = tokenResolver.extractExtraUserId(token);
//        User sender = userRestTemplate.getUser(userId);
        User reciever = userRestTemplate.getUserByUserId(ItemPostedUserId);
        List<ChatResponse> chat =  chatRepository.getChatsByLoginUserForItem(userId, reciever.getId(), itemId)
                .stream()
                .map(c -> mapper.toChatResponse(c, userId, itemId))
                .toList();
        return chat;

    }

    public List<ChatResponse> getChatsListForReportedUserForItem(String token, String ItemPostedUserId, int itemId) {
        final int userId = tokenResolver.extractExtraUserId(token);
        User reciever = userRestTemplate.getUserByUserId(ItemPostedUserId);
        List<ChatResponse> chat = null;
        if (userId == reciever.getId()) {
            chat = chatRepository.getChatsListLoginUserForItem(reciever.getId(), itemId)
                    .stream()
                    .map(c -> mapper.toChatResponse(c, reciever.getId(), itemId))
                    .collect(Collectors.toList());
        }
        return chat;

    }

    public String createChat(String token, String receiverId, int itemId) {
        final int userId = tokenResolver.extractExtraUserId(token);
        User reciever = userRestTemplate.getUserByUserId(receiverId);
        Optional<Chat> existingChat = chatRepository.getChatsByLoginUserForItem(userId, reciever.getId(), itemId);
        if (existingChat.isPresent()) {
            return existingChat.get().getId();
        }


        Chat chat = new Chat();
        chat.setSender(userId);
        chat.setRecipient(reciever.getId());
        chat.setItemId(itemId);

        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
}
