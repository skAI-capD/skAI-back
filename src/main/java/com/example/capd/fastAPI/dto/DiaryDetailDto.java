package com.example.capd.fastAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DiaryDetailDto {
    private LocalDateTime date;
    private String fixedContent;
    private String imageUrl;
    private String capturedImageUrl;
    private String color;
}
