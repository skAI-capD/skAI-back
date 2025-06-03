package com.example.capd.ProverbCorrect.service;

import com.example.capd.ProverbCorrect.dto.ProverbResponseDTO;
import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.domain.Member;

import java.util.List;

public interface ProverbService {

    public void saveMemberProverb(Member member, Long proverbId, boolean isCorrect);
    public List<ProverbResponseDTO> getProverbByProverbType(String type , Member member);

    }
