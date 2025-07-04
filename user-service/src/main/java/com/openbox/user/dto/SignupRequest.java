package com.openbox.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {

    @Schema(description = "사용자 이메일 주소", example = "user@example.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "사용자 비밀번호", example = "password123")
    @NotBlank
    private String password;

    @Schema(description = "사용자 닉네임", example = "재영")
    @NotBlank
    private String nickname;
}