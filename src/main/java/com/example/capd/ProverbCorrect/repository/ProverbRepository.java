package com.example.capd.ProverbCorrect.repository;

import com.example.capd.domain.Proverb;
import com.example.capd.domain.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProverbRepository extends JpaRepository<Proverb, Long> {
    List<Proverb> findByType(String type);
    @Query("""
    SELECT p FROM Proverb p
    WHERE p.type = :type AND 
          p.id NOT IN (
              SELECT mp.proverb.id FROM MemberProverb mp
              WHERE mp.member.id = :memberId AND mp.isCorrect = true
          )
    """)
    List<Proverb> findUnsolvedByTypeAndMemberId(@Param("type") String type, @Param("memberId") Long memberId);

}