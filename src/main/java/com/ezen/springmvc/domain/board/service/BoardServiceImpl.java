package com.ezen.springmvc.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper boardMapper;

	@Override
	public void register(BoardDTO board) {
		boardMapper.create(board);
	}

	@Override
	public List<BoardDTO> getList() {
		return boardMapper.findAll();
	}

	
}
