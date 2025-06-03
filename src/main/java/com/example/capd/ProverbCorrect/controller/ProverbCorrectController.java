// SentenceCorrectController.java
package com.example.capd.ProverbCorrect.controller;

import com.example.capd.ProverbCorrect.service.ProverbService;
import com.example.capd.SentenceCorrect.dto.SentenceResponseDto;
import com.example.capd.SentenceCorrect.service.SentenceService;
import com.example.capd.domain.Member;
import com.example.capd.joinMember.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.capd.ProverbCorrect.dto.ProverbResponseDTO;


@RestController
@RequestMapping("/api/proverbs")
@RequiredArgsConstructor
public class ProverbCorrectController {

    private final ProverbService proverbService;
    private final JoinRepository joinRepository;


    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProverbResponseDTO>> getProverbsByType(@PathVariable String type ,
                                                                      @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        List<ProverbResponseDTO> responses = proverbService.getProverbByProverbType(type,member);
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{proverbId}")
    public ResponseEntity<String> submitProverbAnswer(@PathVariable Long proverbId,
                                                      @RequestParam boolean isCorrect,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("인증 정보 없음");
        }

        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        proverbService.saveMemberProverb(member, proverbId, isCorrect);

        return ResponseEntity.ok("속담 정답 기록 완료");
    }
}



