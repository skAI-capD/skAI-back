package com.example.capd.joinMember.service;



import com.example.capd.joinMember.converter.JoinConverter;
import com.example.capd.domain.Member;
import com.example.capd.joinMember.dto.JoinRequestDTO;
import com.example.capd.joinMember.repository.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinCommandServiceImpl implements JoinCommandService{
    private final JoinRepository joinRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(JoinRequestDTO.JoinDto request) {
        Member newMember = JoinConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        return joinRepository.save(newMember);
    }



}
