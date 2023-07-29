package com.lec.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.Member;
import com.lec.persistence.MemberRepository;
import com.lec.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Member getMember(Member member) {
		
		Optional<Member> findMember = memberRepository.findById(member.getId());
		if(findMember.isPresent()) return findMember.get();
		else return null;
	}
	@Override // https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
	public Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("id")) {
			return memberRepository.findByIdContaining(searchWord, pageable);
		}else {
			return memberRepository.findByNameContaining(searchWord, pageable);
		}
	}
	@Override
	public long getTotalRowCount(Member member) {
		return memberRepository.count();
	}
	@Override
	public void insertMember(Member member) {
		 memberRepository.save(member);
	}

	
	@Override
	public void deleteMember(Member member) {
		// 경고메세지 띄워보기
		memberRepository.deleteById(member.getId());
		
	}


	@Override
	public void updateMember(Member member) {
		 memberRepository.save(member);
	}
	@Override
	public void registerMember(Member member) {
		 memberRepository.save(member);
		
	}
	@Override
	public void modifyMember(Member member) {
		memberRepository.save(member);
		
	}
	@Override
	public int idCheck(String id) throws Exception{
		
		return memberRepository.idCheck(id);
	}
	@Override
	public void eraseMember(Member member) {
		memberRepository.deleteById(member.getId());
		
	}

}
