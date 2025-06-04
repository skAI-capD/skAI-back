package com.example.capd.dictation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DictationRequestDto {
    private MultipartFile image;
    private Long dictationId;
}
