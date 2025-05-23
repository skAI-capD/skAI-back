package com.example.capd.findPw.service;

import com.example.capd.domain.Member;
import com.example.capd.findPw.dto.PasswordResetRequestDTO;
import com.example.capd.findPw.dto.PasswordResetTokenDTO;

public interface PasswordResetService {
    void sendResetLink(PasswordResetRequestDTO request);
    void resetPassword(PasswordResetTokenDTO request);
}
