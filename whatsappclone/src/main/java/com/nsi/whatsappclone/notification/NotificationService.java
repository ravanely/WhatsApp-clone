package com.nsi.whatsappclone.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 5:57 p.m.
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(String userId, Notification notification) {
        log.info("sending WS notification to {} with payload {}", userId, notification);
    }
}
