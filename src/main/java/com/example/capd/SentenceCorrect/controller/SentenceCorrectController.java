// SentenceCorrectController.java
package com.example.capd.SentenceCorrect.controller;

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

@RestController
@RequestMapping("/api/sentences")
@RequiredArgsConstructor
public class SentenceCorrectController {

    private final SentenceService sentenceService;
    private final JoinRepository joinRepository;

    @GetMapping("/shuffle/next")
    public ResponseEntity<List<SentenceResponseDto>> getNextShuffledSentence(
            @AuthenticationPrincipal UserDetails userDetails ,
            @RequestParam String level) {
        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Long memberId = member.getId();  // ✅ 현재 로그인한 사용자 ID
        List<SentenceResponseDto> result = sentenceService.getNextShuffledSentenceByMemberAndLevel(memberId , level);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/{sentenceId}")
    public ResponseEntity<String> markSentenceCorrect(@PathVariable Long sentenceId,
                                                      @RequestParam boolean isCorrect,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("인증 정보 없음");
        }

        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        sentenceService.saveMemberSentence(member, sentenceId, isCorrect);

        return ResponseEntity.ok("정답 기록 완료");
    }

}
