package com.example.capd.fastAPI.controller;



import com.example.capd.domain.Member;
import com.example.capd.fastAPI.domain.DiaryRequestDto;
import com.example.capd.fastAPI.domain.DiaryResponseDto;

import com.example.capd.fastAPI.service.FastApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class FastApiController {

    private final FastApiService fastApiService;

    @PostMapping("/generate")
    public DiaryResponseDto generateDiary(@RequestBody DiaryRequestDto dto,
                                          @AuthenticationPrincipal Member member) {
        return fastApiService.requestDiaryFromFastApi(dto);
    }

}
