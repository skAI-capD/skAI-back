package com.example.capd.ProverbCorrect.repository;

import com.example.capd.domain.MemberProverb;
import com.example.capd.domain.MemberSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProverbRepository extends JpaRepository<MemberProverb, Long> {
}