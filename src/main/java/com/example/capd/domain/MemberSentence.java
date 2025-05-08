package com.example.capd.domain;


import com.example.capd.domain.Member;
import jakarta.persistence.*;
import lombok.*;

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


  public long getMemberSentencePk() {
    return id;
  }

  public void setMemberSentencePk(long id) {
    this.id = id;
  }


}
