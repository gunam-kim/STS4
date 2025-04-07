package com.kh.start.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.vo.Board;

@Mapper
public interface BoardMapper {
	void save(Board board);
	
	List<BoardDTO> findAll(RowBounds rb);
	
	BoardDTO findById(Long boardNo);
	
	@Update("UPDATE TB_BOOT_BOARD SET BOARD_TITLE = #{boardTitle}, BOARD_CONTENT = # {boardContent}, BOARD_FILE_URL = #{boardFileUrl} WHERE BOARD_NO = #{boardNo}")
	void update(BoardDTO board);
	
	@Update("UPDATE TB_BOOT_BOARD SET STATUS = 'N' WHERE BOARD_NO = #{boardNo}")
	void deleteById(Long boardNo);
}
