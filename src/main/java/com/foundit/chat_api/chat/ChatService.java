package com.foundit.chat_api.chat;


import com.foundit.chat_api.common.TokenResolver;
import com.foundit.chat_api.common.UserRestTemplate;
import com.foundit.chat_api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRestTemplate userRestTemplate;
    private final TokenResolver tokenResolver;
    private final ChatMapper mapper;

    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByLoginUserForItem(String token, String ItemPostedUserId, int itemId) {
        final String userId = tokenResolver.extractExtraUserId(token);
        User sender = userRestTemplate.getUser(Long.parseLong(userId));
        User reciever = userRestTemplate.getUserByUserId(ItemPostedUserId);
        return chatRepository.getChatsByLoginUserForItem(sender.getId(), reciever.getId(), itemId)
                .stream()
                .map(c -> mapper.toChatResponse(c, userId, itemId))
                .toList();
    }

    public String createChat(String token, String receiverId, int itemId) {
        final String userId = tokenResolver.extractExtraUserId(token);
        Optional<Chat> existingChat = chatRepository.getChatsByLoginUserForItem(Integer.parseInt(userId), Integer.parseInt(receiverId), itemId);
        if (existingChat.isPresent()) {
            return existingChat.get().getId();
        }


        Chat chat = new Chat();
        chat.setSender(Integer.parseInt(userId));
        chat.setRecipient(Integer.parseInt(receiverId));
        chat.setItemId(itemId);

        Chat savedChat = chatRepository.save(chat);
        return savedChat.getId();
    }
}
