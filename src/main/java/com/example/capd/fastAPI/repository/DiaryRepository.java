package com.example.capd.fastAPI.repository;

import com.example.capd.domain.Diary;
import com.example.capd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByMember(Member member);

}
