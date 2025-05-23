package com.example.capd.domain;

import com.example.capd.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberProverb {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "proverb_id")
  private Proverb proverb;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  private boolean isCorrect;

  private LocalDateTime correctDate;


  public long getMemberproverbPk() {
    return id;
  }

  public void setMemberproverbPk(long id) {
    this.id = id;
  }

  public void setCorrect(boolean correct) {
    this.isCorrect = correct;
  }

  public void setCorrectDate(LocalDateTime correctDate) {
    this.correctDate = correctDate;
  }
}