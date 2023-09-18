package com.zerobase.footballcinema.member.dto;

import com.zerobase.footballcinema.member.domain.Member;
import com.zerobase.footballcinema.member.type.MemberType;
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
    private String email;
    private MemberType memberType;

    public Member toEntity() {
      return Member.builder()
          .username(this.getUsername())
          .password(this.getPassword())
          .email(this.getEmail())
          .memberType(this.getMemberType())
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
    private MemberType memberType;

    public static Response fromEntity(Member member) {
      return Response.builder()
          .username(member.getUsername())
          .regDate(member.getRegDate())
          .memberType(member.getMemberType())
          .build();
    }
  }
}
