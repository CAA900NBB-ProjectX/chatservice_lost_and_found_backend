package com.foundit.chat_api.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {

    @Query(name = ChatConstants.FIND_CHAT_BY_SENDER_ID)
    List<Chat> findChatsBySenderId(@Param("senderId") String senderId, @Param("itemId") int itemId);

    @Query(name = ChatConstants.FIND_CHAT_BY_LOGIN_USER_ITEM_ID)
    Optional<Chat> getChatsByLoginUserForItem(@Param("senderId") String id, @Param("recipientId") String recipientId, @Param("itemId") int itemId);
}
