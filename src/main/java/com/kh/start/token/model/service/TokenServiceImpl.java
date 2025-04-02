package com.kh.start.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.start.auth.util.JwtUtil;

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
	
	@Override
	public Map<String, String> generateToken(String username) {
		// 1) + 2) 
		Map<String, String> tokens = createToken(username);
		return tokens;
	}
	
	private Map<String, String> createToken(String username){
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> tokens = new HashMap();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		
		return tokens;
	}
}
