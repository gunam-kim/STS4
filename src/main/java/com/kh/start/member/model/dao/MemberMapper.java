package com.kh.start.member.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	@Insert("INSERT INTO TB_BOOT_MEMBER VALUES(SEQ_BM.NEXTVAL, #{memberId}, #{memberPw}, #{memberName}, #{role})")
	int signUp(Member member);
	
	@Select("SELECT MEMBER_NO memberNo, MEMBER_ID memberId, MEMBER_PW memberPw, MEMBER_NAME memberName, ROLE FROM TB_BOOT_MEMBER WHERE MEMBER_ID = #{memberId}")
	MemberDTO getMemberByMemberId(String memberId);
}