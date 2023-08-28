package com.ezen.springmvc.domain.article.service;

import java.util.List;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.common.web.PageParams;

public interface ArticleService {

	/** 게시글 작성 */
	public void regist(ArticleDTO articleDTO);

	/** 게시판 상세보기 */
	public ArticleDTO showArticle(int articleId);

	/** 게시글 수정 */
	public void updateArticle(int articleId, ArticleDTO articleDTO);

	/** 게시글 삭제 */
	public boolean deleteArticle(ArticleDTO articleDTO, int articleId, int groupNo, int boardId);

	/** 답글 및 대댓글 작성 */
	public void reply(ArticleDTO articleDTO, int articleId, int boardId, int groupNo);

	/** 페이징관련 메서드... */
	public List<ArticleDTO> getList(PageParams pageParams);
	
	public int getCount(PageParams pageParams);

}
