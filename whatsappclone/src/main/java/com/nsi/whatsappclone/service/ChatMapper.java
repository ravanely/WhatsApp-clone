package com.nsi.whatsappclone.service;

import com.nsi.whatsappclone.chat.ChatResponse;
import com.nsi.whatsappclone.model.Chat;
import com.nsi.whatsappclone.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 12:32 p.m.
 **/
@Service
@RequiredArgsConstructor
public class ChatMapper {
    private final ChatRepository chatRepository;

    public ChatResponse toChatResponse(Chat chat, String userId) {
        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getChatName(userId))
                .unreadCount(chat.getUnreadMessages(userId))
                .lastMessage(chat.getLastMessage())
                .isRecipientOnline(chat.getRecipient().isUserOnline())
                .senderId(chat.getSender().getId())
                .receiverId(chat.getRecipient().getId())
                .build();
    }
}
