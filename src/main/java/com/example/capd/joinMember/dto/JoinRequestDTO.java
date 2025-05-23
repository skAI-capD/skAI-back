package com.example.capd.joinMember.dto;

import com.example.capd.domain.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class JoinRequestDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinDto{
        private String kidsname;
        private String email;
        private String password;}
}
