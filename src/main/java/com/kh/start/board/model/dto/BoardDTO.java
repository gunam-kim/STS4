package com.kh.start.board.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFileUrl;
	private String status;
	private Date createDate;
	private Date modifyDate;
}
