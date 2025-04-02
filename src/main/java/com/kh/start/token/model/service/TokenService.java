package com.kh.start.token.model.service;

import java.util.Map;

public interface TokenService {
	/*
 		1) AccessToken 만들기
 		2) RefreshToken 만들기
 		3) RefreshToken Table에 INSERT하기
 		4) 만료기간이 끝난 RefreshToken DELETE하기
 		5) 사용자가 RefreshToken으로 증명하려할 때 DB가서 조회해오기
	*/
	
	// 1) + 2)
	Map<String, String> generateToken(String username, Long memberNo);
	
	Map<String, String> refreshToken(String refreshToken);
}
