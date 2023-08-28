package com.ezen.springmvc.domain.article.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.PageParams;


/**
 * article 테이블 관련 명세
 */
@Mapper
public interface ArticleMapper {

	 /** 신규 게시글 등록 */
	   public void create(ArticleDTO articleDTO);
	   /**   게시글 수정 */
	   public void updateArticle(Map<String, Object> parameters);
	   
	   /** 게시글 삭제 전 orderNo 조정하기*/
	   public void beforeRemoveUpdateOrderNo(@Param("boardId")int boardId, 
	                                  @Param("groupNo")int groupNo,
	                                  @Param("articleId")int articleId);
	   
	   /** 게시글 삭제*/
	   public boolean removeArticle(ArticleDTO articleDTO);
	   /**   답글 작성*/
	   public void reply(ArticleDTO articleDTO);
	   
	   /** 대댓글 달기 전 orderNo 조정하기*/
	   public void updateOrderNo(@Param("boardId")int boardId, 
	                         @Param("groupNo")int groupNo,
	                         @Param("articleId")int articleId);
	   /**   대댓글 작성*/
	   public void replyComent(@Param("article") ArticleDTO articleDTO, @Param("articleId")int articleId);
	   /**   게시판 상세보기*/
	   public ArticleDTO openArticle(int articleId);
	   // 페이징 계산에 필요한 게시글 전체 갯수 반환 (검색값 포함해서)
	   public int getCountAll(PageParams pageParams);
	   // 요청 페이지, 페이지당 보여지는 목록 갯수에 따른 목록 반환
	   public List<ArticleDTO> findByAll(PageParams pageParams);


}
