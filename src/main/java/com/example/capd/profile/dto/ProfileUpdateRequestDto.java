package com.example.capd.profile.dto;


import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProfileUpdateRequestDto {
    private String kidsname;
    private String  birthday;
    private String nickname;
    private String gender;
    private String image;
}
