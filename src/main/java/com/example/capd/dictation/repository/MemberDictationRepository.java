package com.example.capd.dictation.repository;

import com.example.capd.domain.Dictation;
import com.example.capd.domain.Member;
import com.example.capd.domain.MemberDictation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberDictationRepository extends JpaRepository<MemberDictation, Long> {

    // 받아쓰기 문제(dictation) 기준 조회
    Optional<MemberDictation> findByMemberAndDictation(Member member, Dictation dictation);

    // 특정 회원 + 받아쓰기 문제 기준 조회 (더 안전하게 사용 가능)

    @Query("""
    SELECT d FROM Dictation d
    WHERE d.level = :level
    AND d.id NOT IN (
        SELECT md.dictation.id FROM MemberDictation md
        WHERE md.member.id = :memberId
        AND md.status = com.example.capd.domain.enums.Status.CORRECT
    )
""")
    List<Dictation> findNotCorrectedByLevelAndMemberId(@Param("level") String level, @Param("memberId") Long memberId);

}
