package com.example.capd.ProverbCorrect.converter;

import com.example.capd.ProverbCorrect.dto.ProverbResponseDTO;
import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.domain.Proverb;
import com.example.capd.domain.Sentence;

import java.time.LocalDateTime;

public class ProverbConverter {

    public static ProverbResponseDTO toDto(Proverb proverb) {
        return ProverbResponseDTO.builder()
                .id(proverb.getProverbPk())
                .content(proverb.getContent())
                .imageUrl(proverb.getImageUrl())
                .shortContent(proverb.getShortContent())
                .correct(proverb.getCorrect())
                .wrong1(proverb.getWrong1())
                .wrong2(proverb.getWrong2())
                .wrong3(proverb.getWrong3())
                .type(proverb.getType())
                .status(proverb.getStatus())
                .level_id(proverb.getLevel_id())
                .build();
    }

}