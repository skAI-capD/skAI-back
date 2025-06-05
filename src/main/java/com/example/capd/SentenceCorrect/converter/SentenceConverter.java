package com.example.capd.SentenceCorrect.converter;

import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.domain.Sentence;

public class SentenceConverter {

    public static SentenceResponseDto toDto(Sentence sentence) {
        return SentenceResponseDto.builder()
                .id(sentence.getSentencePk())
                .content(sentence.getContent())
                .order(sentence.getOrders())
                .level_id(sentence.getLevel_id())
                .level(sentence.getLevel())
                .build();
    }

}