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
public class Proverb {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String correct;

  @Column(nullable = true)
  private String content;

  @Column(nullable = true , name = "image_url")
  private String imageUrl;

  @Column(nullable = true)
  private String shortContent;
  private String type;
  private LocalDateTime correctDate;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCORRECT'")
  private Status status;

  private String wrong1;
  private String wrong2;
  private String wrong3;

  @OneToMany(mappedBy = "proverb", cascade = CascadeType.ALL)
  private List<MemberProverb> memberProverbs = new ArrayList<>();

  public long getProverbPk() {
    return id;
  }

  public void setProverbPk(long id) {
    this.id = id;
  }


  public String getCorrect() {
    return correct;
  }

  public void setCorrect(String correct) {
    this.correct = correct;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getImage() {
    return imageUrl;
  }

  public void setImage(String image) {
    this.imageUrl = image;
  }


  public String getShortContent() {
    return shortContent;
  }

  public void setShortContent(String shortContent) {
    this.shortContent = shortContent;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public LocalDateTime getCorrectDate() {
    return correctDate;
  }

  public void setCorrectDate(LocalDateTime correctDate) {
    this.correctDate = correctDate;
  }




  public String getWrong1() {
    return wrong1;
  }

  public void setWrong1(String wrong1) {
    this.wrong1 = wrong1;
  }


  public String getWrong2() {
    return wrong2;
  }

  public void setWrong2(String wrong2) {
    this.wrong2 = wrong2;
  }


  public String getWrong3() {
    return wrong3;
  }

  public void setWrong3(String wrong3) {
    this.wrong3 = wrong3;
  }

}
