package com.example.capd.WordCorrect.controller;

import com.example.capd.WordCorrect.dto.WordAnswerRequestDTO;
import com.example.capd.WordCorrect.dto.WordResponseDTO;
import com.example.capd.WordCorrect.service.WordService;
import com.example.capd.domain.Member;
import com.example.capd.joinMember.repository.JoinRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/word")
public class WordController {

    private final WordService wordService;
    private final JoinRepository joinRepository;
    @GetMapping("/level")
    public ResponseEntity<List<WordResponseDTO>> getWordsByLevel(@RequestParam String level) {
        List<WordResponseDTO> words = wordService.getWordsByLevel(level);
        return ResponseEntity.ok(words);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody WordAnswerRequestDTO request,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();

        System.out.println("🔥 컨트롤러 도달 - 사용자 이메일: " + email);

        if (email == null) {
            return ResponseEntity.status(401).body("인증 정보 없음");
        }

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        wordService.submitAnswer(request, member);
        return ResponseEntity.ok("정답 제출 완료");
    }


}
