package com.kh.start.member.model.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.Member;

@Mapper
public interface MemberMapper {
	@Insert("INSERT INTO TB_BOOT_MEMBER VALUES(SEQ_BM.NEXTVAL, #{memberId}, #{memberPw}, #{memberName}, #{role})")
	int signUp(Member member);
	
	@Select("SELECT MEMBER_NO memberNo, MEMBER_ID memberId, MEMBER_PW memberPw, MEMBER_NAME memberName, ROLE FROM TB_BOOT_MEMBER WHERE MEMBER_ID = #{memberId}")
	MemberDTO getMemberByMemberId(String memberId);
	
	@Update("UPDATE TB_BOOT_MEMBER SET MEMBER_PW = #{encodedPassword} WHERE MEMBER_NO = #{memberNo}")
	void changePassword(Map<String, Object> changeRequest);
	
	@Delete("DELETE FROM TB_BOOT_MEMBER WHERE MEMBER_NO = #{memberNo}")
	void deleteByPassword(Long memberNo);
}