package com.ezen.springmvc.domain.article.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.article.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.web.PageParams;
import com.ezen.springmvc.domain.common.web.Pagination;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private Pagination page;
	private final ArticleMapper articleMapper;

	@Override
	@Transactional
	public void regist(ArticleDTO articleDTO) {
		articleMapper.create(articleDTO);
	}

	@Override
	@Transactional
	public ArticleDTO showArticle(int articleId) {
		return articleMapper.openArticle(articleId);
	}

	@Override
	@Transactional
	public void updateArticle(int articleId, ArticleDTO articleDTO) {
	       Map<String, Object> parameters = new HashMap<>();
	       parameters.put("articleId", articleId);
	       parameters.put("subject", articleDTO.getSubject());
	       parameters.put("content", articleDTO.getContent());

	       articleMapper.updateArticle(parameters);
	}

	@Override
	@Transactional
	public boolean deleteArticle(ArticleDTO articleDTO, int articleId, int groupNo, int boardId) {
		articleMapper.beforeRemoveUpdateOrderNo(boardId, groupNo, articleId);
		return articleMapper.removeArticle(articleDTO);
	}

	@Override
	@Transactional
	public void reply(ArticleDTO articleDTO, int articleId, int boardId, int groupNo) {
		int levelNo = articleDTO.getLevelNo();
		if (levelNo > 0) {
			if (levelNo == 1) {
				articleMapper.reply(articleDTO);
			} else if (levelNo == 2) {
				articleMapper.updateOrderNo(boardId, groupNo, articleId);
				articleMapper.replyComent(articleDTO, articleId);
			}
		}
	}
	
	@Override
	@Transactional
	public List<ArticleDTO> getList(PageParams pageParams) {
		return articleMapper.findByAll(pageParams);
	}
	
	@Override
	@Transactional
	public int getCount(PageParams pageParams) {
		return articleMapper.getCountAll(pageParams);
	}

}
