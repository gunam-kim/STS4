package com.kh.start.member.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/*
	VO (Value Object)
	: 도메인에서 한개 또는 그 이상의 속성들을 묶어 특정 값을 나타내는 객체
	: setter가 없다
*/
@Value
@Getter
@Builder
public class Member {
	private Long memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String role;
}