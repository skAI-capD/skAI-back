package com.example.capd.domain;

import com.example.capd.domain.Member;
import com.example.capd.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDictation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dictation_id")
  private Dictation dictation;

  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCORRECT'")
  private Status status;
  private boolean isCorrect;

  private LocalDateTime correctDate;

  public void setCorrectDate(LocalDateTime correctDate) {
    this.correctDate = correctDate;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
