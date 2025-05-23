package com.example.capd.WordCorrect.converter;

import com.example.capd.WordCorrect.dto.WordResponseDTO;
import com.example.capd.domain.Word;


public class WordConverter {

    private WordResponseDTO convertToDTO(Word word) {
        return WordResponseDTO.builder()
                .id(word.getWordPk())
                .word(word.getWords())
                .content(word.getContent())
                .imageUrl(word.getImageUrl())
                .shortContent(word.getShortContent())
                .level(word.getLevel())
                .hint(word.getHint())
                .wordClass(word.getWordClass())
                .status(word.getStatus() != null ? word.getStatus().toString() : null)
                .correctDate(word.getCorrectDate())
                .build();
    }
}

