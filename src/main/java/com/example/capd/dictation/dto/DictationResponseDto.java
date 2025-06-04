package com.example.capd.dictation.dto;

import com.example.capd.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DictationResponseDto {
    private String extractedText;
    private String correctAnswer;
    private Status status;
    private String level;
    private String levelId;
}
