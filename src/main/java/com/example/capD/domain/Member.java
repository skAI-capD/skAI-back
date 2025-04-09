package com.example.capD.domain;


import com.example.capD.domain.enums.memberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nickname;
  private String birthday;
  private String email;
  private String kidsname;
  private String image;
  private String createdAt;
  private long correct;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "VARCHAR(15) DEFAULT 'INACTIVE'")
  private memberStatus status;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberDictation> memberDictationList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberProverb> memberProverbs = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberSentence> memberSentences = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<Memberword> memberwords = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private List<MemberDiary> memberDiaries = new ArrayList<>();


  public long getMemberId() {
    return id;
  }

  public void setMemberId(long id) {
    this.id = id;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getKidsname() {
    return kidsname;
  }

  public void setKidsname(String kidsname) {
    this.kidsname = kidsname;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  public long getCorrect() {
    return correct;
  }

  public void setCorrect(long correct) {
    this.correct = correct;
  }




}
