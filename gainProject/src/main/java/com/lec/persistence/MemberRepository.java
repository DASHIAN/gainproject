package com.lec.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lec.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    Page<Member> findByIdContaining(String searchWord, Pageable pageable);

    Page<Member> findByNameContaining(String searchWord, Pageable pageable);

    @Query("select count(m) from Member m where m.id = :id")
    int idCheck(@Param("id") String id);
}
