package com.kh.start.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.board.model.dao.BoardMapper;
import com.kh.start.board.model.dto.BoardDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardMapper boardMapper;
	
	@Override
	public void save(BoardDTO board, MultipartFile file) {
	}

	@Override
	public List<BoardDTO> findAll(int pageNo) {
		return null;
	}

	@Override
	public BoardDTO findById(Long boardNo) {
		return null;
	}

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		return null;
	}

	@Override
	public void deleteById(Long boardNo) {
	}

}
