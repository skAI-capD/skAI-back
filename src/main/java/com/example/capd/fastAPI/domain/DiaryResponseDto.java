package com.example.capd.fastAPI.domain;


import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class DiaryResponseDto {
    private long id;
    private String correctedText;
    private String mainScene;
    private String characterPrompt;
    private String prompt;
    private Map<String, String> imageUrls;
    private String fixedText;
    private String capturedImageUrl;
    private String color;
    private LocalDate date;

}
