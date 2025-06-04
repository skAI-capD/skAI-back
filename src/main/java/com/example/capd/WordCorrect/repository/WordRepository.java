package com.example.capd.WordCorrect.repository;

import com.example.capd.domain.Proverb;
import com.example.capd.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByLevel(String level);

    @Query("""
    SELECT w FROM Word w
    WHERE w.level = :level AND 
          w.id NOT IN (
              SELECT mw.word.id FROM Memberword mw
              WHERE mw.member.id = :memberId AND mw.isCorrect = true
          )
    """)
    List<Word> findUnsolvedByLevelAndMemberId(@Param("level") String level, @Param("memberId") Long memberId);

}