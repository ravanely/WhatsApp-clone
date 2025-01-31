package com.nsi.whatsappclone.service;

import com.nsi.whatsappclone.dto.MessageResponse;
import com.nsi.whatsappclone.model.Message;
import org.springframework.stereotype.Service;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 4:23 p.m.
 **/
@Service
public class MessageMapper {
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .type(message.getType())
                .state(message.getState())
                .createdAt(message.getCreatedDate())
                // todo read the media file
                .build();
    }
}
