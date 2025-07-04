package com.example.capd.fastAPI.controller;

import com.example.capd.domain.Member;
import com.example.capd.fastAPI.domain.DiaryResponseDto;
import com.example.capd.fastAPI.service.FastApiService;
import com.example.capd.joinMember.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class FastApiController {

    private final FastApiService fastApiService;
    private final JoinRepository joinRepository;

    @PostMapping("/generate")
    public DiaryResponseDto generateDiary(@RequestParam("diaryImage") MultipartFile diaryImage,
                                          @RequestParam("diaryText") String diaryText,
                                          @RequestParam("style") String style,
                                          @RequestParam("color") String color,
                                          @RequestParam("useCustom") boolean useCustom,
                                          @RequestParam(value = "hairstyle", required = false) String hairstyle,
                                          @RequestParam(value = "outfit", required = false) String outfit,
                                          @AuthenticationPrincipal UserDetails userDetails) throws Exception {

        if (userDetails == null) {
            throw new IllegalArgumentException("인증 정보 없음");
        }

        String email = userDetails.getUsername();
        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return fastApiService.handleDiaryGeneration(member, diaryImage, diaryText, style, color, useCustom, hairstyle, outfit);
    }
}