package com.example.capd.joinMember.converter;


import com.example.capd.domain.Member;
import com.example.capd.domain.enums.Role;
import com.example.capd.domain.enums.memberStatus;
import com.example.capd.joinMember.dto.JoinRequestDTO;
import com.example.capd.joinMember.dto.JoinResponseDTO;

import java.time.LocalDateTime;

public class JoinConverter {
        public static JoinResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
            return JoinResponseDTO.JoinResultDTO.builder()
                    .memberId(member.getId())
                    .createdAt(LocalDateTime.now())
                    .build();
        }

    public static Member toMember(JoinRequestDTO.JoinDto request){

        return Member.builder()
                .kidsname(request.getKidsname())
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname("NICkNAME")
                .birthday(null)
                .status(memberStatus.INACTIVE)
                .correct(0)
                .createdAt(LocalDateTime.now())
                .image(null)
                .role(Role.USER)   // 추가된 코드
                .correct(0L) // 숫자 기본값
                .build();
    }
    }

