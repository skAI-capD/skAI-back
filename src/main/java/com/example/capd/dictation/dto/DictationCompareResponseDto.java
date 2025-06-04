package com.example.capd.dictation.dto;

import com.example.capd.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DictationCompareResponseDto {
    private Status status;
}
