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
public class MemberSentence {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sentence_id")
  private Sentence sentence;

  private boolean isCorrect;

  private LocalDateTime correctDate;

  public long getMemberSentencePk() {
    return id;
  }

  public void setMemberSentencePk(long id) {
    this.id = id;
  }

  public void setCorrect(boolean correct) {
    this.isCorrect = correct;
  }

  public void setCorrectDate(LocalDateTime correctDate) {
    this.correctDate = correctDate;
  }
}
