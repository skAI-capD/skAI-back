package com.example.capd.fastAPI.service;

import com.example.capd.fastAPI.domain.DiaryRequestDto;
import com.example.capd.fastAPI.domain.DiaryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
@RequiredArgsConstructor
public class FastApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public DiaryResponseDto requestDiaryFromFastApi(DiaryRequestDto dto) {
        String url = "http://localhost:8081/generate-diary-image";

        // ⚠️ multipart/form-data로 데이터 구성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("diaryText", dto.getDiaryText());
        body.add("gender", dto.getGender());
        body.add("style", dto.getStyle());
        body.add("color", dto.getColor());
        body.add("useCustom", String.valueOf(dto.isUseCustom()));  // boolean → 문자열
        body.add("hairstyle", dto.getHairstyle() != null ? dto.getHairstyle() : "");
        body.add("outfit", dto.getOutfit() != null ? dto.getOutfit() : "");

        // ⚠️ 이미지 파일이 존재한다면 추가
        if (dto.getDiaryImagePath() != null) {
            File file = new File(dto.getDiaryImagePath());
            if (file.exists()) {
                FileSystemResource resource = new FileSystemResource(file);
                body.add("diaryImage", resource);
            }
        }

        // ⚠️ 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // ⚠️ 요청 구성
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // ⚠️ POST 요청
        ResponseEntity<DiaryResponseDto> response = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, DiaryResponseDto.class
        );

        return response.getBody();
    }
}

