package com.example.capd.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WordOfSentence {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sentence_id")
  private Sentence sentence;

  private String words;


  public long getWordPk() {

    return id;
  }

  public void setWordPk(long id) {

    this.id = id;
  }

  public String getWords() {
    return words;
  }

  public void setWords(String words) {
    this.words = words;
  }

}
