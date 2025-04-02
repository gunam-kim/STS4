package com.kh.start.member.model.service;

import com.kh.start.member.model.dto.ChangePasswordDTO;
import com.kh.start.member.model.dto.MemberDTO;

public interface MemberService {
	void signUp(MemberDTO member);
	
	void changePassword(ChangePasswordDTO passwordEntity);
	
	void deleteByPassword(String password);
}