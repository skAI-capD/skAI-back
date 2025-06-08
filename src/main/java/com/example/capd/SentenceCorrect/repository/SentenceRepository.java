package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.Sentence;
import com.example.capd.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findBySentenceId(int sentenceId);

    @Query("SELECT MAX(s.sentenceId) FROM Sentence s JOIN MemberSentence ms ON s.id = ms.sentence.id WHERE ms.member.id = :memberId AND s.level = :level AND ms.isCorrect = true")
    Integer findMaxCorrectSentenceIdByMemberIdAndLevel(@Param("memberId") Long memberId, @Param("level") String level);
    List<Sentence> findBySentenceIdAndLevel(int sentenceId, String level);


}