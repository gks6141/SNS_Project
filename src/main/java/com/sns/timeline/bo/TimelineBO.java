package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;


@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	
	// input: userId(로그인 된 사람 번 
	// output: List<cardView>
	
	public List<CardView> generateCardViewList(Integer userId){
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글목록을 가져온다. List<PostEntity>
		List<PostEntity> postList = postBO.getPostEntityList();
		// 글목록 반복문 순회
		for(PostEntity post : postList) {
				CardView card = new CardView();			
				//글
				card.setPost(post);
				// 글쓴이
				UserEntity user = userBO.getUserEntityById(card.getPost().getUserId());
				
				card.setUser(user);
				
				//댓글 N개
				//댓글을 card에 넣는다
				List<CommentView> CommentList = commentBO.generateCommentViewListByPostId(card.getPost().getId());
				card.setCommentList(CommentList);
				
				//좋아요 N개
				int likeSum = likeBO.likeCount(card.getPost().getId());
				card.setLike(likeSum);
				
				// 좋아요 클릭 여부
				
				boolean heart = likeBO.heartLikeByPostIdUserId(userId, card.getPost().getId());
				card.setHeart(heart);
				System.out.println(heart);
				
				//!!!!!!! 반드시 리스트에 넣는다.
				cardViewList.add(card);
		}
		
		
		// postEntity -> cardView   ->cardViewList에 넣기
		
		
		return cardViewList;
	}

}
