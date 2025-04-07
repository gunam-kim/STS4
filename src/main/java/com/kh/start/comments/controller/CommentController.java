package com.kh.start.comments.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.comments.model.dto.CommentDTO;
import com.kh.start.comments.model.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity<?> insertComment(@Valid @RequestBody CommentDTO comment){
		commentService.insertComment(comment);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> selectCommentList(@RequestParam(name="boardNo") String boardNo) {
		return ResponseEntity.ok(commentService.selectCommentList(Long.parseLong(boardNo)));
	}
}