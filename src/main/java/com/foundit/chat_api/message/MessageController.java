package com.foundit.chat_api.message;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
@Tag(name = "Message")
public class MessageController {

    private final MessageService messageService;

//    @PostMapping("/save")
//    @ResponseStatus(HttpStatus.CREATED)
    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public MessageResponse saveMessage(MessageRequest message) {

        MessageResponse response = messageService.saveMessage(message);
        return response;
    }

//    @PostMapping(value = "/upload-media", consumes = "multipart/form-data")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void uploadMedia(
//            @RequestParam("chat-id") String chatId,
//            @Parameter
//            @RequestPart("file") MultipartFile file,
//            String token
//    ) {
//        messageService.uploadMediaMessage(chatId, file, token);
//    }
//
//    @PatchMapping
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void setMessageToSeen(@RequestParam("chat-id") String chatId, String token) {
//        messageService.setMessagesToSeen(chatId, token);
//    }

    @GetMapping("/chat/{chat-id}")
    public ResponseEntity<List<MessageResponse>> getAllMessages(
            @PathVariable("chat-id") String chatId
    ) {

        return ResponseEntity.ok(messageService.findChatMessages(chatId));
    }



//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public String sendMessage(testChat message) {
//        System.out.println("📩 Received message: " + message.getMessage());
//        return "Server Response: " + message.getMessage();
//    }
}
