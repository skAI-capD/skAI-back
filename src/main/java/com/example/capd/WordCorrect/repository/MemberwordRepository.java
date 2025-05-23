package com.example.capd.WordCorrect.repository;

import com.example.capd.domain.Memberword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberwordRepository extends JpaRepository<Memberword, Long> {
}
