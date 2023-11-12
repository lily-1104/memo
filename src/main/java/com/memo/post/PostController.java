package com.memo.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.post.bo.PostBO;
import com.memo.post.domain.Post;

@RequestMapping("/post")
@Controller
public class PostController {
	
	
	@Autowired
	private PostBO postBO;
	
	
	/**
	 * 게시글 리스트
	 * @param model
	 * @param session
	 * @return
	 */
	// URL : http://localhost:80/post/post-list-view
	@GetMapping("/post-list-view")
	public String postListView(
			
			// paging
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,	// 비필수라서 value
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,	
			
			Model model, HttpSession session) {
		
		// 로그인 여부 조회
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			
			// 비로그인이면 로그인 화면으로 이동
			return "redirect:/user/sign-in-view";
		}
		
		List<Post> postList = postBO.getPostListByUserId(userId, prevIdParam, nextIdParam);
		
		// paging
		int nextId = 0;
		int prevId = 0;
		
		if (postList.isEmpty() == false) {	// 비어있지 않을 때
			
			// postList가 비어있을 때 오류를 방지하기 위함
			nextId = postList.get(postList.size() - 1).getId();		// 가져온 리스트의 가장 끝 값 (제일 작은 id)
			prevId = postList.get(0).getId();
			
			// 이전 방향의 끝인가?
			// prevId와 post 테이블의 가장 큰 id 값과 같다면 이전 페이지 없음
			if (postBO.isPrevLastPageByUserId(prevId, userId)) {
				prevId = 0;
			}
			
			// 다음 방향의 끝인가?
			// nextId와 post 테이블의 가장 작은 id 값과 같다면 다음 페이지 없음
			if (postBO.isNextLastPageByUserId(nextId, userId)) {
				
				nextId = 0;
			}
		}
		
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "post/postList");
		
		return "template/layout";
		
	}
	
	
	/**
	 * 글쓰기 화면
	 * @param model
	 * @return
	 */
	// URL : http://localhost:80/post/post-create-view
	@GetMapping("/post-create-view")
	public String postCreateView(Model model) {
		
		model.addAttribute("viewName", "post/postCreate");
		
		return "template/layout";
		
	}
	
	
	
	// 글 상세 화면
	// URL : http://localhost:80/post/post-detail-view?postId=4
	@GetMapping("/post-detail-view")
	public String postDetailView(
			@RequestParam("postId") int postId,
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		// DB Select
		// select where postId and userId => 글쓴이(posiId)와 userId가 일치할 때 그 DB를 가져옴
		Post post = postBO.getPostByPostIdAndUserId(postId, userId);
		
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/postDetail");
		
		return "template/layout";
		
	}
	

}
