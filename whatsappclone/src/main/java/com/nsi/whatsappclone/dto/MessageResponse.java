package com.nsi.whatsappclone.dto;

import com.nsi.whatsappclone.utils.MessageState;
import com.nsi.whatsappclone.utils.MessageType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 4:19 p.m.
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {
    private Long id;
    private String content;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private MessageState state;
    private LocalDateTime createdAt;
    private byte[] media;
}
