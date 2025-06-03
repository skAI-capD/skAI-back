package com.example.capd.ProverbCorrect.service;

import com.example.capd.ProverbCorrect.converter.ProverbConverter;

import com.example.capd.ProverbCorrect.dto.ProverbResponseDTO;
import com.example.capd.ProverbCorrect.repository.MemberProverbRepository;
import com.example.capd.ProverbCorrect.repository.ProverbRepository;

import com.example.capd.domain.*;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProverbServiceImpl implements ProverbService {

    private final ProverbRepository proverbRepository;
    private final MemberProverbRepository memberProverbRepository;

    public List<ProverbResponseDTO> getProverbByProverbType(String type , Member member) {
        Long memberId = member.getMemberId();
        List<Proverb> proverbList = proverbRepository.findUnsolvedByTypeAndMemberId(type,memberId);

        return proverbList.stream()
                .map(ProverbConverter::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveMemberProverb(Member member, Long proverbId, boolean isCorrect) {
        Proverb proverb = proverbRepository.findById(proverbId)
                .orElseThrow(() -> new IllegalArgumentException("해당 속담을 찾을 수 없습니다."));

        MemberProverb memberProverb = MemberProverb.builder()
                .member(member)
                .proverb(proverb)
                .isCorrect(isCorrect)
                .correctDate(LocalDateTime.now())
                .build();

        memberProverbRepository.save(memberProverb);
    }
}
