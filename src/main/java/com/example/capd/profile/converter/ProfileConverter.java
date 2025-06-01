package com.example.capd.profile.converter;

import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.domain.Member;
import com.example.capd.domain.Sentence;
import com.example.capd.profile.dto.ProfileResponseDto;

public class ProfileConverter {

    public static ProfileResponseDto toDto(Member member) {
        return ProfileResponseDto.builder()
                .id(member.getId())
                .birthday(member.getBirthday())
                .email(member.getEmail())
                .kidsname(member.getKidsname())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .image(member.getImage())
                .build();
    }

}