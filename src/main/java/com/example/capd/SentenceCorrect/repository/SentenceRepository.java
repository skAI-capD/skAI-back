package com.example.capd.SentenceCorrect.repository;

import com.example.capd.domain.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    List<Sentence> findBySentenceId(int sentenceId);
}