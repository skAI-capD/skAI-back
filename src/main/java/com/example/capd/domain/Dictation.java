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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Dictation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String correctAnswer;
  private String soundsUrl;
  private String content;
  private String level;
  private LocalDateTime correctDate;
  private String level_id;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCORRECT'")
  private Status status;

  @OneToMany(mappedBy = "dictation", cascade = CascadeType.ALL)
  private List<MemberDictation> memberAgreeList = new ArrayList<>();

  public long getDictationPk() {
    return id;
  }

  public void setDictationPk(long id) {
    this.id = id;
  }


  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public void setCorrectDate(LocalDateTime correctDate) {
    this.correctDate = correctDate;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getSoundsUrl() {
    return soundsUrl;
  }

  public void setSoundsUrl(String soundsUrl) {
    this.soundsUrl = soundsUrl;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }




}
