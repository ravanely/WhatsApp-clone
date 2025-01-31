package com.nsi.whatsappclone.service;

import com.nsi.whatsappclone.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : dim. 19 janvier 2025 4:04 p.m.
 **/
@Service
public class UserMapper {
    public User fromTokenAttributes(Map<String, Object> attributes) {
        User user = new User();

        if (attributes.containsKey("sub")) {
            user.setId(attributes.get("sub").toString());
        }
        if (attributes.containsKey("given_name")) {
            user.setFirstName(attributes.get("given_name").toString());
        } else if (attributes.containsKey("nickname")) {
            user.setFirstName(attributes.get("nickname").toString());
        }
        if (attributes.containsKey("family_name")) {
            user.setLastName(attributes.get("family_name").toString());
        }
        if (attributes.containsKey("email")) {
            user.setEmail(attributes.get("email").toString());
        }

        user.setLastSeen(LocalDateTime.now());
        return user;
    }
}
