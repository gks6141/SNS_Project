package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

// view 용 객체
// 글 1개와 mapping
@ToString
@Data
public class CardView {
	// 글 1개
	private PostEntity post;
	// 글쓴이 정보
	private UserEntity user;
	// 댓글 N개
	private List<CommentView> commentList;
	// 좋아요 N개
	private int like;
	// 좋아요 클릭 여부
	private Boolean heart;

	
}
