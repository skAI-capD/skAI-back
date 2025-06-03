package com.example.capd.ProverbCorrect.dto;

import com.example.capd.domain.enums.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProverbResponseDTO {
    private Long id;
    private String content;
    private String imageUrl;
    private String shortContent;
    private String correct;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    private String type;
    private Status status;
    private String level_id;
}
