package com.nsi.whatsappclone.service;

import com.nsi.whatsappclone.dto.UserResponse;
import com.nsi.whatsappclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 5:37 p.m.
 **/
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsersExceptSelf(Authentication connectedUser) {
        return userRepository.findAllUserByExceptSelf(connectedUser.getName()).stream().map(userMapper::toUserResponse).toList();
    }
}
