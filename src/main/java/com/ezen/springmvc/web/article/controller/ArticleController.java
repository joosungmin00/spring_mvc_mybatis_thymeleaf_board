package com.ezen.springmvc.web.article.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springmvc.domain.article.dto.ArticleDTO;
import com.ezen.springmvc.domain.article.service.ArticleService;
import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.service.BoardService;
import com.ezen.springmvc.domain.common.web.PageParams;
import com.ezen.springmvc.domain.common.web.Pagination;
import com.ezen.springmvc.domain.member.dto.Member;
import com.ezen.springmvc.domain.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class ArticleController {

	private final ArticleService articleService;
	private final BoardService boardService;
	
//	@GetMapping("/board/{boardId}/{page}")
//	public String list(Model model, @PathVariable int boardId, @PathVariable int page) {
//		
//		PageParams pageParams = PageParams.builder()
//				.elementSize(10)
//				.pageSize(5)
//				.requestPage(page)
//				.rowCount(0)
//				.boardId(boardId)
//				.build();
//		pageParams.setRowCount(articleService.getCount(pageParams)); 
//		Pagination pagination = new Pagination(pageParams);
//		List<ArticleDTO> list = articleService.getList(pageParams);
//		List<BoardDTO> boardList = boardService.getList();
//		model.addAttribute("boardList", boardList);
//		
//		String boardTitle = boardList.stream()
//	            .filter(board -> board.getBoardId() == boardId)
//	            .findFirst()
//	            .map(BoardDTO::getTitle)
//	            .orElse(null);
//	    model.addAttribute("boardTitle", boardTitle);
//		model.addAttribute("list", list);
//		model.addAttribute("pagination", pagination);
//		return "article/list";
//	}
	
	@GetMapping("/article/{articleId}")
	public String showArticle(@PathVariable int articleId, Model model) {
	    ArticleDTO article = articleService.showArticle(articleId);
	    model.addAttribute("article", article);
	      
	    return "article/read";
	}
	
	
//	전체리스트 검색 및 조건 검색 
	@GetMapping("/board/{boardId}/{page}")
	public String searchArticle(@SessionAttribute(name="loginMember", required = false) Member loginMember,
			@RequestParam(required = false) String type, @RequestParam(required = false) String input, 
			@PathVariable int boardId, @PathVariable int page, Model model) {
		
		PageParams pageParams = PageParams.builder()
				.elementSize(10)
				.pageSize(5)
				.requestPage(page)
				.rowCount(0)
				.boardId(boardId)
				.input(input)
				.type(type)
				.build();
		pageParams.setRowCount(articleService.getCount(pageParams)); 
		Pagination pagination = new Pagination(pageParams);
		List<ArticleDTO> list = articleService.getList(pageParams);
		List<BoardDTO> boardList = boardService.getList();
		model.addAttribute("boardList", boardList);
		model.addAttribute("loginMember",loginMember);
		String boardTitle = boardList.stream()
	            .filter(board -> board.getBoardId() == boardId)
	            .findFirst()
	            .map(BoardDTO::getTitle)
	            .orElse(null);
		model.addAttribute("boardId", boardId);
	    model.addAttribute("boardTitle", boardTitle);
		model.addAttribute("list", list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("type", pageParams.getType());
		model.addAttribute("input", pageParams.getInput());
		
		
		return "article/list";
	}
	
	
	//  수정
	  @GetMapping("/article/update/{articleId}")
	  public String showUpdateForm(@PathVariable int articleId, Model model) {
	      ArticleDTO article = articleService.showArticle(articleId);
	      model.addAttribute("article", article);
	      return "article/read2";
	  }
  
	  @PostMapping("/article/{articleId}")
	  public String updateArticle(@PathVariable int articleId, @ModelAttribute ArticleDTO articleDTO) {
		  articleDTO.setArticleId(articleId);
	      articleService.updateArticle(articleId, articleDTO);
	      return "redirect:/article/" + articleId;
	  }
	
	  
//	  생성
	  @GetMapping("/board/{boardId}/create")
	   public String create(Model model, ArticleDTO articleDTO, @PathVariable int boardId) {
	      model.addAttribute("articleDTO", articleDTO);
	      model.addAttribute("boardId", boardId);
	      return "article/register";
	   }
	   
	   @PostMapping("/board/{boardId}/create")
	   public String regist(@PathVariable int boardId,
	                      @ModelAttribute("articleDTO") ArticleDTO articleDTO, 
	                      RedirectAttributes redirectAttributes,
	                      Model model) {
		   articleDTO.setBoardId(boardId);
	      articleService.regist(articleDTO);
	      model.addAttribute("articleDTO",articleDTO);
	      
	      redirectAttributes.addFlashAttribute("boardId",  articleDTO.getBoardId());
	      return "redirect:/board/{boardId}/1";
	   }
	   
	   
	   
}
