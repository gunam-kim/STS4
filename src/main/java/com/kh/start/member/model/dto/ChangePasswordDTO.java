package com.kh.start.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
	@Size(min=4, max=15, message="비밀번호는 5~15자 사이로 입력하세요")
	@NotBlank(message="현재 비밀번호를 입력하세요")
	private String currentPassword;
	@Size(min=4, max=15, message="비밀번호는 5~15자 사이로 입력하세요")
	@NotBlank(message="새 비밀번호를 입력하세요")
	private String newPassword;
}
