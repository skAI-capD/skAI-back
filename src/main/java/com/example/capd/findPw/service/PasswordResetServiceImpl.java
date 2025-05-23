package com.example.capd.findPw.service;

import com.example.capd.domain.Member;
import com.example.capd.findPw.dto.PasswordResetRequestDTO;
import com.example.capd.findPw.dto.PasswordResetTokenDTO;
import com.example.capd.findPw.entity.PasswordResetToken;
import com.example.capd.findPw.repository.PasswordResetTokenRepository;
import com.example.capd.joinMember.repository.JoinRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.TransactionDefinition;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final JoinRepository joinRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    private final PlatformTransactionManager transactionManager;

    @Override
    @Transactional
    public void sendResetLink(PasswordResetRequestDTO request) {
        Member member = joinRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        TransactionTemplate newTx = new TransactionTemplate(transactionManager);
        newTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        newTx.execute(status -> {
            tokenRepository.deleteByMemberId(member.getId());
            return null;
        });

        PasswordResetToken token = PasswordResetToken.builder()
                .member(member)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusHours(1))
                .build();

        tokenRepository.save(token);
        mailService.sendPasswordResetMail(member.getEmail(), token.getToken());
    }

    @Override
    @Transactional
    public void resetPassword(PasswordResetTokenDTO request) {
        PasswordResetToken token = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("토큰이 만료되었습니다. 이메일을 통해 다시 요청해주세요.");
        }

        Member member = token.getMember();
        member.encodePassword(passwordEncoder.encode(request.getNewPassword()));
        joinRepository.save(member);
        tokenRepository.deleteByToken(request.getToken());
    }
}
