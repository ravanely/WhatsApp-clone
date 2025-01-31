package com.nsi.whatsappclone.security;

import com.nsi.whatsappclone.model.User;
import com.nsi.whatsappclone.repository.UserRepository;
import com.nsi.whatsappclone.service.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : dim. 19 janvier 2025 4:01 p.m.
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class UserSynchronizer {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void synchronizerWithIdp(Jwt token) {
        log.info("Synchronizing user with ip");
        getUserByEmail(token).ifPresent(email -> {
            log.info("Synchronizer User having email {}", email);
            User user = userMapper.fromTokenAttributes(token.getClaims());

            userRepository.save(user);
        });
    }

    private Optional<String> getUserByEmail(Jwt token) {
        Map<String, Object> attributes = token.getClaims();
        if (attributes.containsKey("email")) {
            return Optional.of(attributes.get("email").toString());
        }
        return Optional.empty();
    }
}
