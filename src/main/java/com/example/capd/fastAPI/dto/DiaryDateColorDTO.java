package com.example.capd.fastAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class DiaryDateColorDTO {
    private LocalDate date;
    private String color;
}
