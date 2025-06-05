package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.MemberSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface MemberSentenceRepository extends JpaRepository<MemberSentence, Long> {

    @Query("SELECT MAX(ms.sentence.sentenceId) FROM MemberSentence ms " +
            "WHERE ms.member.id = :memberId AND ms.sentence.level = :level AND ms.isCorrect = true")
    Integer findMaxCorrectSentenceIdByMemberIdAndLevel(@Param("memberId") Long memberId,
                                                       @RequestParam String level);


}