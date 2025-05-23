package com.example.capd.findPw.controller;

import com.example.capd.apiPayload.ApiResponse;
import com.example.capd.findPw.dto.PasswordResetRequestDTO;
import com.example.capd.findPw.service.PasswordResetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordResetApiController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/reset-link")
    public ApiResponse<String> sendResetLink(@RequestBody @Valid PasswordResetRequestDTO request) {
        passwordResetService.sendResetLink(request);
        return ApiResponse.onSuccess("이메일을 확인해 주세요.");
    }
}
