package com.zerobase.footballcinema.member.domain;

import com.zerobase.footballcinema.member.type.MemberType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;
  private String username;

  private String password;

  private String email;
  private String provider;
  private String provided;

  @CreatedDate
  private LocalDateTime regDate;

  @LastModifiedDate
  private LocalDateTime modDate;

  @Enumerated(EnumType.STRING)
  private MemberType memberType;

  public List<String> getRoles() {
    List<String> list = new ArrayList<>();
    list.add(memberType.toString());
    return list;
  }
}
