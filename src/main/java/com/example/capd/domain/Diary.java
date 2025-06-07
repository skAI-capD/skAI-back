package com.example.capd.domain;

import com.example.capd.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Diary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  private String content;
  private String imageUrl;
  private String capturedImageUrl;
  private LocalDate date;
  private String fixedContent;
  private String color;
  private String style;
  private String model;


  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INCORRECT'")
  private Status status;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
  private List<MemberDiary> memberDiaries = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id") // FK 컬럼 이름
  private Member member;

  public long getDiaryPk() {
    return id;
  }

  public void setDiaryPk(long id) {
    this.id = id;
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

  public void setModel(String model) {
    this.model = model;
  }


  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }


  public String getFixedContent() {
    return fixedContent;
  }

  public void setFixedContent(String fixedContent) {
    this.fixedContent = fixedContent;
  }

}
