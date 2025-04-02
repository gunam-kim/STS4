package com.kh.start.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.auth.util.JwtUtil;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Override
	public Map<String, String> login(MemberDTO member){
		/*
			그럼 여기서 우리는 무엇을 해야하는가?
			SpringSecurity
			1. 유효성 검증
			: 아이디/비밀번호 값이 입력되었는가
			: 영어/숫자인가
			: 글자수가 적합한가
			2. 아이디가 MEMBER_ID컬럼에 존재하는 아이디인가
			3. 비밀번호는 컬럼에 존재하는 암호문이 사용자가 입력한
			평문으로 만들어진 것이 맞는가
		 */
		
		// 사용자 인증
		Authentication authentication = null;
		try {
			authentication
				= authenticationManager
					.authenticate(
						new UsernamePasswordAuthenticationToken(
								member.getMemberId(),
								member.getMemberPw()));
		} catch (AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호를 잘못 입력하셨습니다");
		}
		
		CustomUserDetails user
			= (CustomUserDetails)authentication.getPrincipal();
		log.info("인증에 성공한 사용자의 정보 : {}\n", user);
		/*
		String accessToken
			= jwtUtil.getAccessToken(user.getUsername());
		String refreshToken
			= jwtUtil.getRefreshToken(user.getUsername());
		*/
		
		/*
			해시코드가 다르면 다른 객체다 (O)
			-> 같은 값으로 해시돌리면 항상 결과가 같다
			해시코드가 같으면 같은 객체다 (X)
		*/
		Map<String, String> loginResponse
			= tokenService.generateToken(user.getUsername(), user.getMemberNo());
		loginResponse.put("memberId", user.getUsername());
		loginResponse.put("memberName", user.getMemberName());
		return loginResponse;
	}
}
