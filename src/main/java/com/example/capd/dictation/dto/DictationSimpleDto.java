package com.example.capd.dictation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DictationSimpleDto {
    private Long id;
    private String levelId;
    private String soundsUrl;
}