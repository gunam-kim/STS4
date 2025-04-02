package com.kh.start.auth.model.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
@Getter
@Builder
@ToString
public class CustomUserDetails implements UserDetails {
	private Long memberNo;
	private String username;
	private String password;
	private String memberName;
	private Collection<? extends GrantedAuthority> authorities;
}
