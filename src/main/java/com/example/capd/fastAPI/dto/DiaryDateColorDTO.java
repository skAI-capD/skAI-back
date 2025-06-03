package com.example.capd.fastAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class DiaryDateColorDTO {
    private LocalDateTime date;
    private String color;
}
