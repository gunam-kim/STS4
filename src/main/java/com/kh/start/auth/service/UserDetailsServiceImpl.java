package com.kh.start.auth.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	// AuthenticationManager가 실질적으로 사용자의 정보를 조회하는 클래스
	private final MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO user
			= mapper.getMemberByMemberId(username);
		if(user == null) {
			throw new CustomAuthenticationException("존재하지 않는 사용자입니다");
		}
		
		// 사용자가 입력한 아이디가 테이블에 존재할 때
		
		return CustomUserDetails.builder()
				.memberNo(user.getMemberNo())
				.username(user.getMemberId())
				.password(user.getMemberPw())
				.memberName(user.getMemberName())
				.authorities(
					Collections
					.singletonList(
						new SimpleGrantedAuthority(
								user.getRole())))
				.build();
	}
}
