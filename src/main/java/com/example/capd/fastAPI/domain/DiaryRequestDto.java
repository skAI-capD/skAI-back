package com.example.capd.fastAPI.domain;



import lombok.Data;

@Data
public class DiaryRequestDto {
    private String diaryText;
    private String gender;
    private String style;
    private String color;
    private boolean useCustom;
    private String hairstyle;
    private String outfit;
    private String diaryImagePath; // 이미지 경로
}

