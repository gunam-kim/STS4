package com.kh.start.board.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.start.board.model.dto.BoardDTO;

public interface BoardService {
	void save(BoardDTO board, MultipartFile file);
	
	List<BoardDTO> findAll(int pageNo);
	
	BoardDTO findById(Long boardNo);
	
	BoardDTO update(BoardDTO board, MultipartFile file);
	
	void deleteById(Long boardNo);
}
