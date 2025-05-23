package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}