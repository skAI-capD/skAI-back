package com.example.capd.SentenceCorrect.service;

import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.domain.Member;

import java.util.List;

public interface SentenceService {

    public void saveMemberSentence(Member member, Long sentenceId , boolean isCorrect);
    public List<SentenceResponseDto> getShuffledSentencesBySentenceId(int sentenceId);
}
