package com.example.capd.findPw.controller;

import com.example.capd.findPw.dto.PasswordResetTokenDTO;
import com.example.capd.findPw.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordResetPageController {

    private final PasswordResetService passwordResetService;

    @GetMapping("/reset")
    public String renderResetPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password-form";
    }

    @PostMapping("/reset")
    public String resetPassword(@ModelAttribute PasswordResetTokenDTO request, Model model) {
        passwordResetService.resetPassword(request);
        model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
        return "reset-success";
    }
}
