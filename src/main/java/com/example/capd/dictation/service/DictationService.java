package com.example.capd.dictation.service;

import com.example.capd.dictation.dto.DictationCompareResponseDto;
import com.example.capd.dictation.dto.DictationRequestDto;
import com.example.capd.dictation.dto.DictationResponseDto;
import com.example.capd.dictation.dto.DictationSimpleDto;
import com.example.capd.domain.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DictationService {

    public String extractAndSaveOcrContent(MultipartFile image, Long dictationId, Member member) throws IOException;
    public String sendImageToOcrApi(MultipartFile image) throws IOException;

    public List<DictationSimpleDto> getUnsolvedDictationsByLevel(String level, Long memberId);
    public DictationCompareResponseDto compareDictationContentWithAnswer(Long dictationId, Member member);

    }
