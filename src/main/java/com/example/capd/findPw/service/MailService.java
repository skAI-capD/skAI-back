package com.example.capd.findPw.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendPasswordResetMail(String toEmail, String token) {
        String subject = "[S-KAI] 비밀번호 재설정 안내";
        String link = "http://localhost:8080/password/reset?token=" + token;
        String content = "<p>비밀번호 재설정을 위해 아래 링크를 클릭해주세요:</p>" +
                "<p><a href=\"" + link + "\">비밀번호 재설정</a></p>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(content, true); // true = HTML
            helper.setFrom("wlsaudwo295@naver.com");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 발송 실패", e);
        }
    }
}
