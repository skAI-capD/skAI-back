package com.example.capd.dictation.controller;

import com.example.capd.dictation.dto.*;
import com.example.capd.dictation.service.DictationService;
import com.example.capd.domain.Member;
import com.example.capd.joinMember.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dictation")
public class DictationController {

    private final DictationService dictationService;
    private final JoinRepository joinRepository;



    @PostMapping("/compare")
    public ResponseEntity<DictationCompareResponseDto> compareDictation(
            @RequestBody DictationCompareRequestDto request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        DictationCompareResponseDto result = dictationService.compareDictationContentWithAnswer(request.getDictationId(), member);
        return ResponseEntity.ok(result);
    }



    @GetMapping("/unsolved")
    public ResponseEntity<List<DictationSimpleDto>> getUnsolvedDictationList(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("level") String level) {

        String email = userDetails.getUsername();
        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<DictationSimpleDto> result = dictationService.getUnsolvedDictationsByLevel(level, member.getId());
        return ResponseEntity.ok(result);
    }


    @PostMapping(value = "/ocr/result", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> handleOcrOnly(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("image") MultipartFile image,
            @RequestParam("dictationId") Long dictationId
    ) throws IOException {
        String email = userDetails.getUsername();
        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        String resultContent = dictationService.extractAndSaveOcrContent(image, dictationId, member);
        return ResponseEntity.ok(resultContent);
    }

}
