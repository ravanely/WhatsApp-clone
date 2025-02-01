package com.nsi.whatsappclone.controller;

import com.nsi.whatsappclone.dto.ChatResponse;
import com.nsi.whatsappclone.common.StringResponse;
import com.nsi.whatsappclone.service.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 1:11 p.m.
 **/
@RestController
@RequestMapping("/api/v1/chats")
@Tag(name = "Chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<StringResponse> createChat(
            @RequestParam(name = "sender-id") String senderId,
            @RequestParam(name = "receiver-id") String receiverId) {

        final String chatId = chatService.createChat(senderId, receiverId);

        return ResponseEntity.ok(StringResponse.builder().response(chatId).build());
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByReceiver(Authentication currentUser) {

        return ResponseEntity.ok(chatService.getChatsByReceiverId(currentUser));
    }
}
