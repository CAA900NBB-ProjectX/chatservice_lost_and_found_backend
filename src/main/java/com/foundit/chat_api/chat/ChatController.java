package com.foundit.chat_api.chat;

import com.foundit.chat_api.common.StringResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
@Tag(name = "Chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<StringResponse> createChat(
            @RequestBody ChatDto chatDto
    ) {
        final String chatId = chatService.createChat(chatDto.getToken(), chatDto.getReceiverId(), chatDto.getItemId());
        StringResponse response = StringResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getChatsByLoginUserForItem(@RequestParam String token, @RequestParam String ItemPostedUser, @RequestParam int itemId) {
            List<ChatResponse>  chat = chatService.getChatsByLoginUserForItem(token, ItemPostedUser, itemId);
            if (chat.isEmpty()) {
                return ResponseEntity.ok(0);
            } else {
                return ResponseEntity.ok(chat);
            }
    }

    @GetMapping("/getchatlist")
    public ResponseEntity<?> getChatsForItem(@RequestParam String token, @RequestParam String ItemPostedUser, @RequestParam int itemId) {
        List<ChatResponse>  chat = chatService.getChatsListForReportedUserForItem(token, ItemPostedUser, itemId);
        if (chat.isEmpty()) {
            return ResponseEntity.ok(0);
        } else {
            return ResponseEntity.ok(chat);
        }
    }

}
