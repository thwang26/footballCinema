package com.zerobase.footballcinema.global.security;

import com.zerobase.footballcinema.admin.domain.Admin;
import com.zerobase.footballcinema.admin.repository.AdminRepository;
import com.zerobase.footballcinema.member.domain.Member;
import com.zerobase.footballcinema.member.repository.MemberRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  MemberRepository memberRepository;
  AdminRepository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> memberOptional = memberRepository.findByUsername(username);
    if (memberOptional.isPresent()) {
      Member member = memberOptional.get();
      return new CustomUserDetails(member.getUsername(), member.getPassword(), member.getMemberType());
    }

    Optional<Admin> adminOptional = adminRepository.findByUsername(username);
    if (adminOptional.isPresent()) {
      Admin admin = adminOptional.get();
      return new CustomUserDetails(admin.getUsername(), admin.getPassword(), admin.getAdminType());
    }

    throw new UsernameNotFoundException("couldn't find user -> " + username);
  }
}
