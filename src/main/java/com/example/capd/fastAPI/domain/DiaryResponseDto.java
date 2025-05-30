package com.example.capd.fastAPI.domain;


import lombok.Data;

@Data
public class DiaryResponseDto {
    private String correctedText;
    private String mainScene;
    private String characterPrompt;
    private String prompt;
    private String imageUrl;
    private String fixedText;
}
