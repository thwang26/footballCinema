package com.zerobase.footballcinema.member.repository;

import com.zerobase.footballcinema.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);
    boolean existsByMemberId(String memberId);
}
