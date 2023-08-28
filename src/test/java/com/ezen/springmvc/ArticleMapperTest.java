package com.ezen.springmvc;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.article.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.web.PageParams;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ArticleMapperTest {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Test
	@Disabled
	public void getCountAllTest() {
		
		PageParams pageParams = PageParams.builder()
									.elementSize(10)
									.pageSize(10)
									.requestPage(1)
									.rowCount(0)
									.boardId(10)
									.input(null)
									.type(null)
									.build();
		
        int count = articleMapper.getCountAll(pageParams);
        log.info("게시글 수 : {}", count);
	}
	
	@Test
	@Disabled
	public void findByAllTest() {
		PageParams pageParams = PageParams.builder()
				.elementSize(10)
				.pageSize(10)
				.requestPage(1)
				.rowCount(0)
				.boardId(10)
				.input("monday")
				.type("writer")
				.build();
		
		List<ArticleDTO> articleDTO = articleMapper.findByAll(pageParams);
		articleDTO.forEach( article -> {
			log.info("검색된 게시글 : {}", article);
		});
	}
//	
//	   @Test
//	   @DisplayName("게시글 등록 테스트")
//	   @Disabled
//	   void createTest() {
//	      ArticleDTO article = ArticleDTO.builder()
//	                              .articleId(68)
//	                              .boardId(10)
//	                              .writer("monday")
//	                              .subject("오늘 점심 뭐 먹지")
//	                              .content("김기정 선생님과 함께 하는 점심.")
//	                              .passwd("1234")
//	                              .groupNo(2)
//	                              .levelNo(0)
//	                              .orderNo(0)
//	                              .build();
//	      
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      mapper.create(article);
//	      log.debug("신규글 내용 :{}",article);
//	   }
//	   
//	   @Test
//	   @DisplayName("게시글 수정 테스트")
//	   @Disabled
//	   void updateArticleTest() {
//	      ArticleDTO article = ArticleDTO.builder()
//	                              .articleId(68)
//	                              .subject("오점무")
//	                              .build();
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      mapper.updateArticle(article);
//	   }
//	   
//	   @Test
//	   @DisplayName("게시글 삭제 테스트")
//	   @Disabled
//	   void removeArticleTest() {
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      mapper.removeArticle(68, "1234");
//	   }
//	   
//	   @Test
//	   @Disabled
//	   void replyTest() {
//	      ArticleDTO article = ArticleDTO.builder()
////	                              .articleId(69)  //시퀀스
//	                              .boardId(10)
//	                              .writer("thursday")
//	                              .subject("목요일 댓글 제목입니다.")
//	                              .content("목요일 댓글 내용입니다.")
//	                              .passwd("1234")
//	                              .groupNo(1)
//	                              .levelNo(0)
//	                              .orderNo(1)
//	                              .build();
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      mapper.reply(article);
//	   }
//	   
//	   /** 게시글 상세보기 테스트*/
//	   @Test
//	   @Disabled
//	   void openArticleTest() {
//	      List<ArticleDTO> opened = null;
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      opened = mapper.openArticle(2);
//	      assertThat(opened).isNotNull().hasSize(1);   
//	    }
//	   
//	   /** 대댓글 작성 전 orderNo 조정*/
//	   @Test
//	   @Disabled
//	   void updateOrderNoTest() {
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      mapper.updateOrderNo(10, 1, 70);
//	   }
//	   
//	   /** 대댓글 작성*/
//	   @Test
//	   void replyComentTest() {
//	      ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
//	      mapper.updateOrderNo(10, 1, 70);
//	      ArticleDTO article = ArticleDTO.builder()
////	                              .articleId(80) 
//	                              .boardId(10)
//	                              .writer("thursday")
//	                              .subject("목요일 대댓글 제목 테스트")
//	                              .content("목요일 대댓글 내용 테스트")
//	                              .passwd("1234")
//	                              .groupNo(1)
//	                              .levelNo(2)
////	                              .orderNo(1)
//	                              .build();
//	            mapper.replyComent(article, 70);
//	   }
//
//
//	@AfterEach
//	public void destory() {
//		sqlSession.close();
//	}
}
