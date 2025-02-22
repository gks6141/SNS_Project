package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

import jakarta.servlet.http.HttpSession;


@Controller
public class TimelineController {

	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/timeline/timeline-view")
	public String timelineView(Model model,HttpSession session) {
		//DB 가져오기
//		List<PostEntity> postList = postBO.getPostEntityList();
//		List<Comment> comments = commentBO.getCommentByPostId();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardViewList = timelineBO.generateCardViewList(userId);
		
		//model넘기기
//		model.addAttribute("postList",postList);
		model.addAttribute("cardViewList", cardViewList);
		
		return "timeline/timeline";
	}
	
	
}
