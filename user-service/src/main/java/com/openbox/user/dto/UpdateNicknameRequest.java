package com.openbox.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateNicknameRequest {

    @NotBlank
    private String nickname;
}