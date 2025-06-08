package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.Sentence;
import com.example.capd.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findBySentenceId(int sentenceId);

    // SentenceRepository.java

    @Query("SELECT MAX(s.level_id) FROM Sentence s JOIN MemberSentence ms ON s.id = ms.sentence.id WHERE ms.member.id = :memberId AND s.level = :level AND ms.isCorrect = true")
    Integer findMaxCorrectLevelIdByMemberIdAndLevel(@Param("memberId") Long memberId, @Param("level") String level);

    @Query("SELECT s FROM Sentence s WHERE s.level = :level AND s.level_id = :levelId")
    List<Sentence> findByLevelAndLevelId(@Param("level") String level, @Param("levelId") int levelId);

    List<Sentence> findBySentenceIdAndLevel(int sentenceId, String level);


}