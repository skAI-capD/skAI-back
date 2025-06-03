package com.example.capd.fastAPI.service;


import com.example.capd.config.S3Uploader;
import com.example.capd.domain.Diary;
import com.example.capd.domain.Member;
import com.example.capd.domain.MemberDiary;
import com.example.capd.domain.enums.Status;
import com.example.capd.fastAPI.domain.DiaryResponseDto;
import com.example.capd.fastAPI.dto.DiaryDateColorDTO;
import com.example.capd.fastAPI.repository.DiaryRepository;
import com.example.capd.fastAPI.repository.MemberDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FastApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final S3Uploader s3Uploader;
    private final DiaryRepository diaryRepository;
    private final MemberDiaryRepository memberDiaryRepository;

    public DiaryResponseDto handleDiaryGeneration(Member member, MultipartFile diaryImage,
                                                  String diaryText, String style, String color,
                                                  boolean useCustom, String hairstyle, String outfit) {

        // 1. MultipartFile → 임시 파일로 저장
        File tempFile = convertMultipartFileToFile(diaryImage);
        String s3ImageUrl = s3Uploader.uploadFromFilePath(tempFile.getAbsolutePath(), "diary");

        // 2. FastAPI 호출
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("diaryText", diaryText);
        body.add("gender", member.getGender());
        body.add("style", style);
        body.add("color", color);
        body.add("useCustom", String.valueOf(useCustom));
        body.add("hairstyle", hairstyle != null ? hairstyle : "");
        body.add("outfit", outfit != null ? outfit : "");
        body.add("diaryImage", new FileSystemResource(tempFile));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<DiaryResponseDto> response = restTemplate.exchange(
                "http://localhost:8081/generate-diary-image",
                HttpMethod.POST,
                requestEntity,
                DiaryResponseDto.class
        );

        // 임시 파일 삭제
        tempFile.delete();

        DiaryResponseDto responseDto = response.getBody();
        if (responseDto == null) {
            throw new IllegalStateException("FastAPI 응답이 없습니다.");
        }

        // 3. FastAPI에서 받은 그림 URL → 다시 S3 업로드
        String generatedImageUrl = responseDto.getImageUrl();
        String s3ReuploadedImageUrl = reuploadImageToS3(generatedImageUrl);

        // 4. DB 저장
        Diary diary = Diary.builder()
                .member(member)
                .content(diaryText)
                .fixedContent(responseDto.getCorrectedText())
                .capturedImageUrl(s3ImageUrl)
                .imageUrl(s3ReuploadedImageUrl)
                .date(LocalDateTime.now())
                .status(Status.CORRECT)
                .color(color)
                .style(style)
                .build();
        diaryRepository.save(diary);

        // 4-1. MemberDiary에도 저장
        MemberDiary memberDiary = MemberDiary.builder()
                .member(member)
                .diary(diary)
                .isCorrect(true)
                .correctDate(LocalDateTime.now())
                .build();
        memberDiaryRepository.save(memberDiary);

        // 5. 최종 응답
        responseDto.setImageUrl(s3ReuploadedImageUrl);

        // 5. 최종 응답
        responseDto.setImageUrl(s3ReuploadedImageUrl);
        return responseDto;
    }

    public List<DiaryDateColorDTO> getDiaryDateColors(Member member) {
        Long memberId = member.getId(); // 안전하게 ID만 꺼냄

        return memberDiaryRepository.findByMemberId(memberId)
                .stream()
                .map(md -> new DiaryDateColorDTO(md.getDiary().getDate(), md.getDiary().getColor()))
                .collect(Collectors.toList());
    }


    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        try {
            Path tempPath = Files.createTempFile("upload_", multipartFile.getOriginalFilename());
            File file = tempPath.toFile();
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            throw new RuntimeException("MultipartFile → File 변환 실패: " + e.getMessage(), e);
        }
    }

    private String reuploadImageToS3(String imageUrl) {
        try (InputStream inputStream = new URL(imageUrl).openStream()) {
            Path tempFile = Files.createTempFile("downloaded_", ".png");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            String uploadedUrl = s3Uploader.uploadFromFilePath(tempFile.toAbsolutePath().toString(), "diary");
            tempFile.toFile().delete();
            return uploadedUrl;
        } catch (Exception e) {
            throw new RuntimeException("FastAPI 이미지 S3 재업로드 실패: " + e.getMessage(), e);
        }
    }
}