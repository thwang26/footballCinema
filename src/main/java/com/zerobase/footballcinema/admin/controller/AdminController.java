package com.zerobase.footballcinema.admin.controller;

import com.zerobase.footballcinema.admin.domain.Admin;
import com.zerobase.footballcinema.admin.dto.SignInRequest;
import com.zerobase.footballcinema.admin.dto.SignUp;
import com.zerobase.footballcinema.admin.service.AdminService;
import com.zerobase.footballcinema.global.security.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

  private final AdminService adminService;
  private final TokenProvider tokenProvider;

  @PostMapping("/sign-up")
  public SignUp.Response signUp(@RequestBody SignUp.Request signUpRequest) {
    log.info("detected new sign-up attempts -> {}", signUpRequest.getUsername());
    return adminService.createAccount(signUpRequest);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
    Admin admin = adminService.authenticate(signInRequest);
    String token = tokenProvider.generateToken(admin.getUsername(), admin.getRoles());
    log.info("admin login -> {}", signInRequest.getUsername());
    return ResponseEntity.ok(token);
  }
}
