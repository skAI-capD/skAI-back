package com.example.capd.SentenceCorrect.dto;

import com.example.capd.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SentenceResponseDto {
    private Long id;
    private String content;
    private int order;
}
