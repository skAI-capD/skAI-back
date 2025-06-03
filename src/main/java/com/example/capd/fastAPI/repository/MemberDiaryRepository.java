package com.example.capd.fastAPI.repository;

import com.example.capd.domain.Member;
import com.example.capd.domain.MemberDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberDiaryRepository extends JpaRepository<MemberDiary, Long> {
    List<MemberDiary> findByMemberId(Long memberId);
    Optional<MemberDiary> findByMemberAndDiaryDate(Member member, LocalDateTime date);



}

