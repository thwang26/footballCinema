package com.zerobase.footballcinema.member.service;

import static com.zerobase.footballcinema.global.exception.ErrorCode.ACCOUNT_ALREADY_REGISTERED;
import static com.zerobase.footballcinema.global.exception.ErrorCode.ACCOUNT_DOES_NOT_EXIST;
import static com.zerobase.footballcinema.global.exception.ErrorCode.PASSWORD_DOES_NOT_MATCH;

import com.zerobase.footballcinema.global.exception.MemberException;
import com.zerobase.footballcinema.member.domain.Member;
import com.zerobase.footballcinema.member.dto.SignInRequest;
import com.zerobase.footballcinema.member.dto.SignUp;
import com.zerobase.footballcinema.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService {

  private final PasswordEncoder passwordEncoder;
  private final MemberRepository memberRepository;

  @Transactional
  public SignUp.Response createAccount(SignUp.Request signUpRequest) {
    if (memberRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new MemberException(ACCOUNT_ALREADY_REGISTERED);
    }
    Member member = signUpRequest.toEntity();
    member.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    Member savedMember = memberRepository.save(member);
    return SignUp.Response.fromEntity(savedMember);
  }

  public Member authenticate(SignInRequest signInRequest) {
    Member member = memberRepository.findByUsername(signInRequest.getUsername())
        .orElseThrow(() -> new MemberException(ACCOUNT_DOES_NOT_EXIST));

    if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())) {
      throw new MemberException(PASSWORD_DOES_NOT_MATCH);
    }

    return member;
  }
}
