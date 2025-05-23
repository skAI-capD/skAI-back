package com.example.capd.fastAPI.service;


import com.example.capd.fastAPI.domain.DiaryRequestDto;
import com.example.capd.fastAPI.domain.DiaryResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FastApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public DiaryResponseDto requestDiaryFromFastApi(DiaryRequestDto dto) {
        String url = "http://localhost:8081/generate-diary-image";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<DiaryRequestDto> request = new HttpEntity<>(dto, headers);

        ResponseEntity<DiaryResponseDto> response = restTemplate.postForEntity(
                url, request, DiaryResponseDto.class
        );

        return response.getBody();
    }
}
