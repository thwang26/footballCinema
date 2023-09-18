package com.zerobase.footballcinema.admin.service;

import static com.zerobase.footballcinema.global.exception.ErrorCode.ACCOUNT_ALREADY_REGISTERED;
import static com.zerobase.footballcinema.global.exception.ErrorCode.ACCOUNT_DOES_NOT_EXIST;
import static com.zerobase.footballcinema.global.exception.ErrorCode.PASSWORD_DOES_NOT_MATCH;

import com.zerobase.footballcinema.admin.domain.Admin;
import com.zerobase.footballcinema.admin.dto.SignInRequest;
import com.zerobase.footballcinema.admin.dto.SignUp;
import com.zerobase.footballcinema.admin.repository.AdminRepository;
import com.zerobase.footballcinema.global.exception.MemberException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminService {

  private final PasswordEncoder passwordEncoder;
  private final AdminRepository memberRepository;

  @Transactional
  public SignUp.Response createAccount(SignUp.Request signUpRequest) {
    if (memberRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new MemberException(ACCOUNT_ALREADY_REGISTERED);
    }
    Admin admin = signUpRequest.toEntity();
    admin.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    Admin savedAdmin = memberRepository.save(admin);
    return SignUp.Response.fromEntity(savedAdmin);
  }

  public Admin authenticate(SignInRequest signInRequest) {
    Admin admin = memberRepository.findByUsername(signInRequest.getUsername())
        .orElseThrow(() -> new MemberException(ACCOUNT_DOES_NOT_EXIST));

    if (!passwordEncoder.matches(signInRequest.getPassword(), admin.getPassword())) {
      throw new MemberException(PASSWORD_DOES_NOT_MATCH);
    }

    return admin;
  }
}
