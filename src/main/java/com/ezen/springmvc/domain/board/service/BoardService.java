package com.ezen.springmvc.domain.board.service;

import java.util.List;

import com.ezen.springmvc.domain.board.dto.BoardDTO;

public interface BoardService {
	
	public void register(BoardDTO board);
	public List<BoardDTO> getList();
	
}
