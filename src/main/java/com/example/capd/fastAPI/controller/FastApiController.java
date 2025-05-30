package com.example.capd.fastAPI.controller;

import com.example.capd.domain.Member;
import com.example.capd.fastAPI.domain.DiaryRequestDto;
import com.example.capd.fastAPI.domain.DiaryResponseDto;
import com.example.capd.fastAPI.service.FastApiService;
import com.example.capd.joinMember.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class FastApiController {

    private final FastApiService fastApiService;
    private final JoinRepository joinRepository;  // ✅ 회원 조회를 위한 의존성 추가

    @PostMapping("/generate")
    public DiaryResponseDto generateDiary(@RequestBody DiaryRequestDto dto,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("인증 정보 없음");
        }

        String email = userDetails.getUsername();

        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 필요 시 member를 dto에 넣거나 로그용으로 사용 가능
        return fastApiService.requestDiaryFromFastApi(dto);
    }

}
