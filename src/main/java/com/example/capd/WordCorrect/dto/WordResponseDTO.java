package com.example.capd.WordCorrect.dto;

import com.example.capd.domain.enums.Status;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordResponseDTO {
    private long id;
    private String word;
    private String content;
    private String imageUrl;
    private String shortContent;
    private String level;
    private String hint;
    private String wordClass;
    private String status;
    private String correctDate;
}
