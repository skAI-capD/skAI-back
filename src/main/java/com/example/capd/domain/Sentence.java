package com.example.capd.domain;

import com.example.capd.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Sentence {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String level;
  private String correctDate;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCORRECT'")
  private Status status;
  private String content;
  private int order;
  @Column(name = "sentence_id")
  private int sentenceId;

  @OneToMany(mappedBy = "sentence", cascade = CascadeType.ALL)
  private List<MemberSentence> memberSentences = new ArrayList<>();

  public long getSentencePk() {
    return id;
  }

  public void setSentencePk(long id) {
    this.id = id;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public String getCorrectDate() {
    return correctDate;
  }

  public void setCorrectDate(String correctDate) {
    this.correctDate = correctDate;
  }




}
