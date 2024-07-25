package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	//input:  output:x
	public void likeToggle(int userId, int postId) {
		//조회
//		Like like = likeMapper.selectLikeByUserIdPostId(userId, postId);
		
		int count = likeMapper.selectLikeCountByPostIdOrUserId(userId, postId);
		if(count > 0) {
			likeMapper.deleteLike(userId, postId);
		} else {
			likeMapper.insertLike(userId,postId);
		}
		
//		//여부  => 삭제 or 추가
//		if(like	!= null) {
//			likeMapper.deleteLike(userId,postId);
//		} else {
//			likeMapper.insertLike(userId,postId);
//		}
		
	}
	
	public int likeCount(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(null , postId);
	}
	
	
//	public Boolean heartToggle(Integer userId, int postId) {
//		return likeMapper.selectHeartByUserIdPostId(userId, postId);
//	}
	
//	public int heartToggle(int userId, int postId) {
//		return likeMapper.selectLikeCountByUserIdPostId(postId,userId);
//	}
	
	// 좋아요 채울지 여부
	//input: postId(필수), userId(로그인/비로그인)   output:boolean(채울지 여부)
	
	public boolean heartLikeByPostIdUserId(Integer userId,int postId) {
		if(userId == null) {
			return false;
		}
		
		// 로그인이면 1.행이 있으면 (1) true 2. 없으면(0) false
		return likeMapper.selectLikeCountByPostIdOrUserId(userId, postId) == 1 ? true: false;
	}
	
	public void deleteLikeByPostId(int postId) {
		likeMapper.deleteLikeByPostId(postId);
	}
	
	
}
