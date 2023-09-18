package com.zerobase.footballcinema.admin.dto;

import com.zerobase.footballcinema.admin.domain.Admin;
import com.zerobase.footballcinema.admin.type.AdminType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SignUp {

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Request {

    private String username;
    private String password;
    private AdminType adminType;

    public Admin toEntity() {
      return Admin.builder()
          .username(this.getUsername())
          .password(this.getPassword())
          .adminType(this.getAdminType())
          .build();
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Response {

    private String username;
    private LocalDateTime regDate;
    private AdminType adminType;

    public static Response fromEntity(Admin admin) {
      return Response.builder()
          .username(admin.getUsername())
          .regDate(admin.getRegDate())
          .adminType(admin.getAdminType())
          .build();
    }
  }
}
