package com.example.capd.WordCorrect.service;

import com.example.capd.WordCorrect.dto.WordAnswerRequestDTO;
import com.example.capd.WordCorrect.dto.WordResponseDTO;
import com.example.capd.domain.Member;

import java.util.List;


public interface WordService {
    List<WordResponseDTO> getWordsByLevel(String level);
    void submitAnswer(WordAnswerRequestDTO request, Member member);
}

