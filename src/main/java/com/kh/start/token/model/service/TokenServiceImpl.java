package com.kh.start.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.start.auth.util.JwtUtil;
import com.kh.start.token.model.dao.TokenMapper;
import com.kh.start.token.model.vo.RefreshToken;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
	/*
		1) AccessToken 만들기
		2) RefreshToken 만들기
		3) RefreshToken Table에 INSERT하기
		4) 만료기간이 끝난 RefreshToken DELETE하기
		5) 사용자가 RefreshToken으로 증명하려할 때 DB가서 조회해오기
	*/
	private final JwtUtil tokenUtil;
	private final TokenMapper tokenMapper;
	
	@Override
	public Map<String, String> generateToken(String username, Long memberNo) {
		// 1) + 2) 
		Map<String, String> tokens = createToken(username);
		// 3)
		saveToken(tokens.get("refreshToken"), memberNo);
		
		// 4)
		tokenMapper.deleteExpiredRefreshToken(System.currentTimeMillis());
		
		// 5)
		return tokens;
	}
	
	private void saveToken(String refreshToken, Long memberNo) {
		RefreshToken token
			= RefreshToken.builder()
				.token(refreshToken)
				.memberNo(memberNo)
				.expiration(System.currentTimeMillis() + 36000000L * 72)
				.build();
		tokenMapper.saveToken(token);
	}
	
	private Map<String, String> createToken(String username){
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> tokens = new HashMap();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}
	
	public Map<String, String> refreshToken(String refreshToken){
		RefreshToken token
			= RefreshToken.builder().token(refreshToken).build();
		RefreshToken responseToken
			= tokenMapper.findByToken(token);
		// 숙제
		// 1. JwtUtil을 이용해 refreshToken을 Parsing한뒤
		// MEMBER_NO 및 token값을 이용해 SELECT하는 구문으로 수정
		// 2. 예외발생 시 예외처리 핸들러 커스텀익셉션 클랙스를 구현해
		// 예외처리기가 처리하게 하기
		
		if(responseToken == null || token.getExpiration() < System.currentTimeMillis()) {
			throw new RuntimeException("유효하지 않은 토큰입니다");
		}
		
		String username = getUsernameByToken(refreshToken);
		Long memberNo = responseToken.getMemberNo();
		
		return generateToken(username, memberNo);
	}
	
	private String getUsernameByToken(String refreshToken) {
		Claims claims = tokenUtil.parseJwt(refreshToken);
		return claims.getSubject();
	}
}
