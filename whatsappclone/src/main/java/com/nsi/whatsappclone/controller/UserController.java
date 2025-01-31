package com.nsi.whatsappclone.controller;

import com.nsi.whatsappclone.dto.UserResponse;
import com.nsi.whatsappclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : ven. 31 janvier 2025 5:43 p.m.
 **/
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsersExceptSelf(Authentication currentUser) {
        return ResponseEntity.ok(userService.getAllUsersExceptSelf(currentUser));
    }
}
