package com.example.capd.findPw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetTokenDTO {
    @NotBlank
    private String token;

    @NotBlank
    private String newPassword;
}
