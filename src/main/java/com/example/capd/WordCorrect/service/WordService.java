package com.example.capd.WordCorrect.service;

import com.example.capd.WordCorrect.dto.WordAnswerRequestDTO;
import com.example.capd.WordCorrect.dto.WordResponseDTO;
import com.example.capd.domain.Member;

import java.util.List;


public interface WordService {
    public List<WordResponseDTO> getWordsByLevel(String level, Member member);
        void submitAnswer(WordAnswerRequestDTO request, Member member);
}

