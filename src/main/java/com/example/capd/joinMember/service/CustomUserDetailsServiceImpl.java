package com.example.capd.joinMember.service;

import com.example.capd.domain.Member;
import com.example.capd.joinMember.repository.JoinRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Getter
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final JoinRepository joinRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = joinRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음: " + email));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().name()) // 반드시 ROLE_ 접두어 필요
                .build();
    }
}
