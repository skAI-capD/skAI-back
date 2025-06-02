package com.example.capd.domain;

import com.example.capd.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Word {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String word;
  private String content;
  private String imageUrl;
  private String shortContent;
  private String level;
  private String hint;
  private String wordClass;
  private LocalDateTime correctDate;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCORRECT'")
  private Status status;


  @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
  private List<Memberword> memberwords = new ArrayList<>();

  public long getWordPk() {
    return id;
  }

  public void setWordPk(long id) {
    this.id = id;
  }

  public String getWords() {
    return word;
  }

  public void setWords(String words) {
    this.word = words;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDateTime getCorrectDate(LocalDateTime correctDate) {
    return correctDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setCorrectDate(LocalDateTime correctDate) {
    this.correctDate = correctDate;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }


  public String getShortContent() {
    return shortContent;
  }

  public void setShortContent(String shortContent) {
    this.shortContent = shortContent;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }







}
