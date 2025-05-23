package com.example.capd.findPw.entity;

import com.example.capd.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime expiryDate;

    public static PasswordResetToken create(Member member) {
        return PasswordResetToken.builder()
                .member(member)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusMinutes(30))
                .build();
    }
}
