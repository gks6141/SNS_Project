package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.domain.Like;
import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	//input:  output:x
	public void likeToggle(int userId, int postId) {
		//조회
		Like like = likeMapper.selectLikeByUserIdPostId(userId, postId);
		
//		int count = likeMapper.selectLikeCountByUserIdPostId(postId,userId);
//		if(count > 0) {
//			likeMapper.deleteLike(userId, postId);
//		} else {
//			likeMapper.insertLike(userId,postId);
//		}
		
		
		//여부  => 삭제 or 추가
		if(like	!= null) {
			likeMapper.deleteLike(userId,postId);
		} else {
			likeMapper.insertLike(userId,postId);
		}
		
	}
	
	public int likeCount(int postId) {
		return likeMapper.likeCount(postId);
	}
	
	
	public Boolean heartToggle(Integer userId, int postId) {
		return likeMapper.selectHeartByUserIdPostId(userId, postId);
	}
}
