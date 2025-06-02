package com.example.capd.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponseDto {
    private Long id;
    private String birthday;
    private String kidsname;
    private String nickname;
    private String gender;
    private String email;
    private String image;
    private LocalDateTime createdAt;
}
