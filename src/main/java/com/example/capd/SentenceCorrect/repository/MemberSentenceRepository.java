package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.MemberSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSentenceRepository extends JpaRepository<MemberSentence, Long> {
}