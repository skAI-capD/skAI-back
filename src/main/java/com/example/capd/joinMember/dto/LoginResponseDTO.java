package com.example.capd.joinMember.dto;

import com.example.capd.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private Long id;
    private String email;
    private String role;
    private String token;  // ✅ JWT 토큰 필드 추가

    public static LoginResponseDTO from(Member member, String token) {
        return LoginResponseDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .role(member.getRole().name())
                .token(token)  // ✅ 토큰 포함
                .build();
    }
}
