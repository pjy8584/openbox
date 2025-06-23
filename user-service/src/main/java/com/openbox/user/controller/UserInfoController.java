package com.openbox.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import com.openbox.user.service.UserService;

import jakarta.validation.Valid;

import com.openbox.user.dto.UpdateNicknameRequest;
import com.openbox.user.dto.UpdatePasswordRequest;
import com.openbox.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        UserResponse userInfo = userService.getMyInfo(email);
        return ResponseEntity.ok(userInfo);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<String> updateNickname(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody @Valid UpdateNicknameRequest request) {
        userService.updateNickname(userDetails.getUsername(), request.getNickname());
        return ResponseEntity.ok("닉네임이 변경되었습니다.");
    }

    @PatchMapping("/password")
    public ResponseEntity<String> updatePassword(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody @Valid UpdatePasswordRequest request) {
        userService.updatePassword(
                userDetails.getUsername(),
                request.getCurrentPassword(),
                request.getNewPassword()
        );
        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }
}