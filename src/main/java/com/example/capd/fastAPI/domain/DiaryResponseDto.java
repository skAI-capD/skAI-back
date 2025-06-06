package com.example.capd.fastAPI.domain;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DiaryResponseDto {
    private String correctedText;
    private String mainScene;
    private String characterPrompt;
    private String prompt;
    private String imageUrl;
    private String fixedText;
    private String capturedImageUrl;
    private String color;
    private LocalDate date;
}
