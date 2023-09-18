package com.zerobase.footballcinema.admin.controller;

import com.zerobase.footballcinema.admin.service.AdminService;
import com.zerobase.footballcinema.global.security.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

  private final AdminService adminService;
  private final TokenProvider tokenProvider;

}
