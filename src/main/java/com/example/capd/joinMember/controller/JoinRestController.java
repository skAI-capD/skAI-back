package com.example.capd.joinMember.controller;


import com.example.capd.apiPayload.ApiResponse;

import com.example.capd.joinMember.converter.JoinConverter;
import com.example.capd.domain.Member;
import com.example.capd.joinMember.dto.JoinRequestDTO;
import com.example.capd.joinMember.dto.JoinResponseDTO;
import com.example.capd.joinMember.dto.LoginRequestDTO;
import com.example.capd.joinMember.dto.LoginResponseDTO;
import com.example.capd.joinMember.repository.JoinRepository;
import com.example.capd.joinMember.service.JoinCommandService;
import com.example.capd.jwt.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import static com.example.capd.joinMember.dto.LoginResponseDTO.from;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class JoinRestController {
        private final JoinCommandService joinCommandService;
        private final AuthenticationManager authenticationManager;
        private final JoinRepository joinRepository;
        private final JwtTokenProvider jwtTokenProvider; // 주입 추가


    @PostMapping("/signup")
    public ApiResponse<JoinResponseDTO.JoinResultDTO> join(@RequestBody @Valid JoinRequestDTO.JoinDto request){
        Member member = joinCommandService.joinMember(request);
        return ApiResponse.onSuccess(JoinConverter.toJoinResultDTO(member));
    }
    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        try {
            // Spring Security 인증 시도
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // DB에서 사용자 조회
            Member member = joinRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("유저 정보 없음"));

            // ✅ 사용자 역할 기반 JWT 생성
            String token = jwtTokenProvider.createToken(member.getEmail(), member.getRole().name());

            // ✅ 로그인 응답에 토큰 포함
            return ApiResponse.onSuccess(LoginResponseDTO.from(member, token));

        } catch (AuthenticationException e) {
            return ApiResponse.onFailure("LOGIN_FAILED", "이메일 또는 비밀번호가 틀렸습니다.", null);
        }
    }



//        @GetMapping("/login")
//        public String loginPage() {
//        return "login";
//    }
//
//        @GetMapping("/home")
//        public String home() {
//        return "home";
//    }
//
//        @GetMapping("/admin")
//        public String admin() {
//        return "admin";
//    }

//        @PostMapping("/signup")
//        public String joinMember(@ModelAttribute("memberJoinDto") JoinRequestDTO.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!
//                                BindingResult bindingResult,
//                                Model model) {
//            if (bindingResult.hasErrors()) {
//                // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
//                return "signup";
//            }
//            try {
//                joinCommandService.joinMember(request);
//                return "redirect:/login";
//            } catch (Exception e) {
//                // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
//                model.addAttribute("error", e.getMessage());
//                return "signup";
//            }
    }




