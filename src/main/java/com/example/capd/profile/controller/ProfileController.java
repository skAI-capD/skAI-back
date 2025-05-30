package com.example.capd.profile.controller;

import com.example.capd.domain.Member;
import com.example.capd.joinMember.repository.JoinRepository;
import com.example.capd.profile.dto.ProfileResponseDto;
import com.example.capd.profile.dto.ProfileUpdateRequestDto;
import com.example.capd.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    private final JoinRepository joinRepository;  // ✅ 추가

    @GetMapping("/profile")
    public ProfileResponseDto getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("인증 정보 없음");
        }

        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return profileService.getProfile(member);
    }

    @PutMapping("set/profile")
    public void updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody ProfileUpdateRequestDto dto) {
        if (userDetails == null) {
            throw new IllegalArgumentException("인증 정보 없음");
        }

        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        profileService.updateProfile(member, dto);
    }

}
