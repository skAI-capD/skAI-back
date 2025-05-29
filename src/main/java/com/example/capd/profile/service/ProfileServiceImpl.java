package com.example.capd.profile.service;

import com.example.capd.domain.Member;
import com.example.capd.profile.converter.ProfileConverter;
import com.example.capd.profile.dto.ProfileResponseDto;
import com.example.capd.profile.dto.ProfileUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    public ProfileResponseDto getProfile(Member member) {
        return ProfileConverter.toDto(member);
    }

    public void updateProfile(Member member, ProfileUpdateRequestDto dto) {
        member.setKidsname(dto.getKidsname());
        member.setBirthday(dto.getBirthday());
        member.setGender(dto.getGender());
        member.setImage(dto.getImage());
        // @Transactional이 있는 경우 자동 반영됨
    }

}
