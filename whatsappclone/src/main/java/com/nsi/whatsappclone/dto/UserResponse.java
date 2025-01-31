package com.nsi.whatsappclone.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 5:39 p.m.
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;
    private boolean isOnline;

}
