package com.example.capd.joinMember.service;


import com.example.capd.domain.Member;
import com.example.capd.joinMember.dto.JoinRequestDTO;

public interface JoinCommandService {
    Member joinMember(JoinRequestDTO.JoinDto request);


}
