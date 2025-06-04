package com.example.capd.dictation.repository;

import com.example.capd.domain.Dictation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictationRepository extends JpaRepository<Dictation, Long> {

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
