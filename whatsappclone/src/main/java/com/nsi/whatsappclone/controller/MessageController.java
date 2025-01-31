package com.nsi.whatsappclone.controller;

import com.nsi.whatsappclone.dto.MessageRequest;
import com.nsi.whatsappclone.dto.MessageResponse;
import com.nsi.whatsappclone.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 4:56 p.m.
 **/
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@RequestBody MessageRequest messageRequest) {
        messageService.saveMessage(messageRequest);
    }

    @PostMapping(value = "/upload-media", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMedia(@RequestParam(name = "chat-id") String chatId, @RequestParam(name = "file") MultipartFile file, Authentication authentication) {
        messageService.uploadMediaMessage(chatId, file, authentication);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setMessageToSeen(@RequestParam(name = "chat-id") String chatId, Authentication authentication) {
        messageService.setMessageToSeen(chatId, authentication);
    }

    @GetMapping("/chat/{chat-id}")
    public ResponseEntity<List<MessageResponse>> getChatMessages(@PathVariable(name = "chat-id") String chatId) {
        return ResponseEntity.ok(messageService.findChatMessages(chatId));
    }
}
