package com.openbox.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import com.openbox.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import com.openbox.user.dto.UpdateNicknameRequest;
import com.openbox.user.dto.UpdatePasswordRequest;
import com.openbox.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "사용자 API", description = "회원 마이페이지, 정보 수정 등")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;

    @Operation(summary = "현재 로그인한 사용자 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        UserResponse userInfo = userService.getMyInfo(email);
        return ResponseEntity.ok(userInfo);
    }

    @Operation(summary = "닉네임 변경")
    @PatchMapping("/nickname")
    public ResponseEntity<String> updateNickname(@AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody @Valid UpdateNicknameRequest request) {
        userService.updateNickname(userDetails.getUsername(), request.getNickname());
        return ResponseEntity.ok("닉네임이 변경되었습니다.");
    }

    @Operation(summary = "비밀번호 변경")
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