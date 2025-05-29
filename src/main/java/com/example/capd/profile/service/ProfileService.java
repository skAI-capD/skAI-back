package com.example.capd.profile.service;

import com.example.capd.domain.Member;
import com.example.capd.profile.dto.ProfileResponseDto;
import com.example.capd.profile.dto.ProfileUpdateRequestDto;

public interface ProfileService {
    public ProfileResponseDto getProfile(Member member);

    public void updateProfile(Member member, ProfileUpdateRequestDto dto);

    }
