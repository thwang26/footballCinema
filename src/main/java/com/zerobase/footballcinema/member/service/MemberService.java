package com.zerobase.footballcinema.member.service;

import static com.zerobase.footballcinema.global.exception.ErrorCode.*;
import com.zerobase.footballcinema.global.exception.MemberException;
import com.zerobase.footballcinema.member.domain.Member;
import com.zerobase.footballcinema.member.dto.SignInRequest;
import com.zerobase.footballcinema.member.dto.SignUp;
import com.zerobase.footballcinema.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + memberId));
    }

    @Transactional
    public SignUp.Response createAccount(SignUp.Request signUpRequest) {
        boolean exists = memberRepository.existsByMemberId(signUpRequest.getMemberId());
        if (exists) {
            throw new MemberException(ACCOUNT_ALREADY_REGISTERED);
        }

        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Member member = memberRepository.save(signUpRequest.toEntity());
        return SignUp.Response.fromEntity(member);
    }

    public Member authenticate(SignInRequest signInRequest) {
        Member member = memberRepository.findByMemberId(signInRequest.getMemberId())
                .orElseThrow(() -> new MemberException(ACCOUNT_DOES_NOT_EXIST));

        if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())) {
            throw new MemberException(PASSWORD_DOES_NOT_MATCH);
        }

        return member;
    }
}
