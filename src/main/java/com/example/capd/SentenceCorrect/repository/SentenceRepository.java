package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.Sentence;
import com.example.capd.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findBySentenceId(int sentenceId);


    @Query("""
    SELECT s FROM Sentence s
    WHERE s.level = :level AND 
          s.id NOT IN (
              SELECT ms.sentence.id FROM MemberSentence ms
              WHERE ms.member.id = :memberId AND ms.isCorrect = true
          )
""")
    List<Sentence> findUnsolvedByLevelAndMemberId(@Param("level") String level, @Param("memberId") Long memberId);

}