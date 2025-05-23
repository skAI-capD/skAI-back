package com.example.capd.joinMember.repository;


import com.example.capd.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
