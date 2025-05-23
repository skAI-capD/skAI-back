package com.example.capd.ProverbCorrect.repository;

import com.example.capd.domain.Proverb;
import com.example.capd.domain.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProverbRepository extends JpaRepository<Proverb, Long> {
    List<Proverb> findByType(String type);
}