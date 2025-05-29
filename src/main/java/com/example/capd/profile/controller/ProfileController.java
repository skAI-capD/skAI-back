package com.example.capd.profile.controller;

import com.example.capd.domain.Member;
import com.example.capd.profile.dto.ProfileResponseDto;
import com.example.capd.profile.dto.ProfileUpdateRequestDto;
import com.example.capd.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile")
    public ProfileResponseDto getProfile(@AuthenticationPrincipal Member member) {
        return profileService.getProfile(member);
    }

    @PutMapping("set/profile")
    public void updateProfile(@AuthenticationPrincipal Member member,
                              @RequestBody ProfileUpdateRequestDto dto) {
        profileService.updateProfile(member, dto);
    }

}
