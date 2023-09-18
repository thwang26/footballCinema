package com.zerobase.footballcinema.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  ACCOUNT_ALREADY_REGISTERED("이미 가입된 회원입니다."),
  ACCOUNT_DOES_NOT_EXIST("회원정보가 존재하지 않습니다."),
  PASSWORD_DOES_NOT_MATCH("비밀번호가 일치하지 않습니다."),
  ;
  private final String description;
}
