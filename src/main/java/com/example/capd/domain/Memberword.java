package com.example.capd.domain;

import com.example.capd.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Memberword {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "word_id")
  private Word word;
  private boolean isCorrect;
  private String correctDate;


  public long getMemberwordPk() {
    return id;
  }

  public void setMemberwordPk(long id) {
    this.id = id;
  }

}
