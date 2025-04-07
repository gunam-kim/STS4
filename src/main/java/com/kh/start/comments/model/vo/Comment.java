package com.kh.start.comments.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Comment {
	private Long commentNo;
	private String commentContent;
	private Long commentWriter;
	private Date createDate;
	private Long refBoardNo;
}
