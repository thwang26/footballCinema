package com.zerobase.footballcinema.admin.domain;

import com.zerobase.footballcinema.admin.type.AdminType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Admin {

  @Id
  private String adminId;

  private String username;

  private String password;

  @CreatedDate
  private LocalDateTime regDate;

  @LastModifiedDate
  private LocalDateTime modDate;

  @Enumerated(EnumType.STRING)
  private AdminType adminType;

  public List<String> getRoles() {
    List<String> list = new ArrayList<>();
    list.add(adminType.toString());
    return list;
  }
}
