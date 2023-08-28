package com.ezen.springmvc;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardMapperTest {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void findAllTest() {
		List<BoardDTO> list = boardMapper.findAll();
		for (BoardDTO boardDTO : list) {
			log.debug("{}", boardDTO);
		}
	}

	@Test
	@Disabled
	@Transactional
	@DisplayName("게시판 생성")
	void createTest() {
		BoardDTO createBoardDTO = BoardDTO.builder()
									.category(1)
									.title("테스트2자유게시판")
									.description("테스트 2")
									.build();
		boardMapper.create(createBoardDTO);
		log.debug("사원등록 완료 {}", createBoardDTO);

	}

}
