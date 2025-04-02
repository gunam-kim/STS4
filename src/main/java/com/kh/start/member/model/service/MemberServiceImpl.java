package com.kh.start.member.model.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.start.exception.MemberIdDuplicateException;
import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public void signUp(MemberDTO member) {
		MemberDTO searchedMember
			= mapper.getMemberByMemberId(member.getMemberId());
		if(searchedMember != null) {
			throw new MemberIdDuplicateException("이미 존재하는 아이디입니다");
		}
		// 할 일
		// 1. 비밀번호 암호화
		// 2. Role 주기
		Member memberValue
			= Member.builder()
			.memberId(member.getMemberId())
			.memberPw(passwordEncoder.encode(member.getMemberPw()))
			.memberName(member.getMemberName())
			.role("ROLE_USER")
			.build();
		mapper.signUp(memberValue);
		log.info("사용자 등록 성공 : {}", member);
	}
}
