package com.example.capd.SentenceCorrect.service;

import com.example.capd.SentenceCorrect.converter.SentenceConverter;
import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.SentenceCorrect.repository.MemberRepository;
import com.example.capd.SentenceCorrect.repository.MemberSentenceRepository;
import com.example.capd.SentenceCorrect.repository.SentenceRepository;
import com.example.capd.domain.Member;
import com.example.capd.domain.MemberSentence;
import com.example.capd.domain.Sentence;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SentenceServiceImpl implements SentenceService {

    private final SentenceRepository sentenceRepository;
    private final MemberSentenceRepository memberSentenceRepository;

    public List<SentenceResponseDto> getShuffledSentencesBySentenceId(int sentenceId) {
        List<Sentence> sentenceList = sentenceRepository.findBySentenceId(sentenceId);

        Collections.shuffle(sentenceList);  // 랜덤 순서
        return sentenceList.stream()
                .map(SentenceConverter::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveMemberSentence(Member member, Long sentenceGroupId, boolean isCorrect) {
        List<Sentence> sentences = sentenceRepository.findBySentenceId(sentenceGroupId.intValue());

        if (sentences.isEmpty()) {
            throw new IllegalArgumentException("해당 sentence_id에 해당하는 문장을 찾을 수 없습니다.");
        }

        for (Sentence sentence : sentences) {
            MemberSentence memberSentence = MemberSentence.builder()
                    .member(member)
                    .sentence(sentence)
                    .isCorrect(isCorrect)
                    .correctDate(LocalDateTime.now())
                    .build();

            memberSentenceRepository.save(memberSentence);
        }
    }

}