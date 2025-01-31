package com.nsi.whatsappclone.notification;

import com.nsi.whatsappclone.utils.MessageType;
import lombok.*;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 5:59 p.m.
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    private String chatId;
    private String content;
    private String senderId;
    private String receiverId;
    private String chatName;
    private MessageType messageType;
    private NotificationType type;
    private byte[] media;
}
