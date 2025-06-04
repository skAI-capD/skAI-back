package com.example.capd.dictation.service;

import com.example.capd.dictation.dto.DictationCompareResponseDto;
import com.example.capd.dictation.dto.DictationRequestDto;
import com.example.capd.dictation.dto.DictationResponseDto;
import com.example.capd.dictation.dto.DictationSimpleDto;
import com.example.capd.dictation.repository.DictationRepository;
import com.example.capd.dictation.repository.MemberDictationRepository;
import com.example.capd.domain.Dictation;
import com.example.capd.domain.MemberDictation;
import com.example.capd.domain.enums.Status;
import com.example.capd.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DictationServiceImpl implements DictationService {

    private final DictationRepository dictationRepository;
    private final MemberDictationRepository memberDictationRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<DictationSimpleDto> getUnsolvedDictationsByLevel(String level, Long memberId) {
        List<Dictation> unsolved = dictationRepository.findUnsolvedByLevelAndMemberId(level, memberId);
        return unsolved.stream()
                .map(d -> new DictationSimpleDto(d.getId(), d.getLevel_id(), d.getSoundsUrl()))
                .collect(Collectors.toList());
    }


    @Override
    public String extractAndSaveOcrContent(MultipartFile image, Long dictationId) throws IOException {
        // 1. 받아쓰기 문제 찾기
        Dictation dictation = dictationRepository.findById(dictationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 받아쓰기 문제를 찾을 수 없습니다."));

        // 2. FastAPI OCR 호출
        String ocrText = sendImageToOcrApi(image);

        // 3. content 필드에 저장 (정답 비교 없음)
        dictation.setContent(ocrText);
        dictationRepository.save(dictation);

        MemberDictation memberDictation = memberDictationRepository.findByDictation(dictation)
                .orElseThrow(() -> new IllegalArgumentException("해당 받아쓰기 결과를 찾을 수 없습니다."));

        memberDictation.setCorrectDate(LocalDateTime.now());
        memberDictation.setStatus(Status.CORRECT);
        memberDictationRepository.save(memberDictation);
        // 4. 저장된 content만 반환
        return ocrText;
    }



    public String sendImageToOcrApi(MultipartFile image) throws IOException {
        String fastApiUrl = "http://ec2-3-37-53-105.ap-northeast-2.compute.amazonaws.com:8081/dictation-ocr" ; // FastAPI OCR 서버 주소

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        File tempFile = Files.createTempFile("ocr_", image.getOriginalFilename()).toFile();
        image.transferTo(tempFile);
        body.add("image", new FileSystemResource(tempFile));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(fastApiUrl, requestEntity, Map.class);
        tempFile.delete();

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (String) response.getBody().get("extractedText");
        }

        throw new RuntimeException("OCR 서버 응답 실패: " + response.getStatusCode());
    }

    public DictationCompareResponseDto compareDictationContentWithAnswer(Long dictationId) {
        Dictation dictation = dictationRepository.findById(dictationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 받아쓰기 문제를 찾을 수 없습니다."));

        Status status = dictation.getCorrectAnswer().trim().equals(dictation.getContent().trim())
                ? Status.CORRECT
                : Status.INCORRECT;

        return new DictationCompareResponseDto(status);
    }




}
