package com.kh.start.auth.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	/*
		JWT (JSON Web Tokens)
	*/
	// application.yml에 정의된 속성의 값을 클래스 내부에서 호출
	@Value("${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	
	@PostConstruct
	public void init() {
		byte[] keyArr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyArr);
	}
	
	public String getAccessToken(String username) {
		return Jwts.builder()
			.subject(username)		// 사용자 이름
			.issuedAt(new Date())	// 발급일
			.expiration(new Date(	// 만료일
				System.currentTimeMillis() + 36000000L * 24))
			.signWith(key)			// 서명
			.compact();
	}
	
	public String getRefreshToken(String username) {
		return Jwts.builder()
			.subject(username)
			.issuedAt(new Date())
			.expiration(new Date(
				System.currentTimeMillis() + 36000000L * 24 * 3))
			.signWith(key)
			.compact();
	}
}