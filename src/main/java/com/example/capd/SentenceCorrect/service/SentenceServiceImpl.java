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

    public List<SentenceResponseDto> getNextShuffledSentenceByMemberAndLevel(Long memberId, String level) {
        // 1. 맞춘 문제 중 가장 큰 sentenceId 찾기
        Integer maxCorrectSentenceId = memberSentenceRepository.findMaxCorrectSentenceIdByMemberIdAndLevel(memberId, level);

        // 2. 다음 문제 ID (아무것도 맞춘 적 없으면 1번부터 시작)
        int nextSentenceId = (maxCorrectSentenceId != null) ? maxCorrectSentenceId + 1 : 1;

        // 3. 다음 문제 문장 조각들 가져오기
        List<Sentence> nextSentences = sentenceRepository.findBySentenceIdAndLevel(nextSentenceId, level);
        if (nextSentences.isEmpty()) {
            throw new IllegalArgumentException("해당 레벨의 다음 문제가 존재하지 않습니다.");
        }

        // 4. 순서 섞기
        Collections.shuffle(nextSentences);

        // 5. 변환 후 반환
        return nextSentences.stream()
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