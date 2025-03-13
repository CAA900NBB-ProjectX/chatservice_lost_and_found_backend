package com.foundit.chat_api.chat;

import com.foundit.chat_api.common.StringResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
@Tag(name = "Chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<StringResponse> createChat(
            String token,
            @RequestParam(name = "receiver-id") String receiverId,
            @RequestParam(name = "item-id") int itemId
    ) {
        final String chatId = chatService.createChat(token, receiverId, itemId);
        StringResponse response = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByLoginUserForItem(String token, @RequestParam String ItemPostedUser, @RequestParam int itemId) {
        return ResponseEntity.ok(chatService.getChatsByLoginUserForItem(token, ItemPostedUser, itemId));
    }
}
