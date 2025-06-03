package com.example.capd.fastAPI.repository;

import com.example.capd.domain.Member;
import com.example.capd.domain.MemberDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDiaryRepository extends JpaRepository<MemberDiary, Long> {
    List<MemberDiary> findByMember(Member member);

}

