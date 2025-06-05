package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.Sentence;
import com.example.capd.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findBySentenceId(int sentenceId);


    List<Sentence> findBySentenceIdAndLevel(int sentenceId, String level);


}