package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostBO {
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	
	public PostEntity addPostEntityByUserIdContentImagePath(int userId, String userLoginId, String content,MultipartFile file) {
		
		String imagePath = null;
		
		
		imagePath =  fileManagerService.uploadFile(file, userLoginId);
		
		
		
		return postRepository.save(PostEntity.builder()
									.userId(userId)
									.content(content)
									.imagePath(imagePath)
									.build());
	}
	
	
	public List<PostEntity> getPostEntityList(){
		return postRepository.findByOrderByIdDesc();
	}
	
	@Transactional
	public void deletePost(int userId, int postId) {
		PostEntity post = postRepository.findByUserIdAndId(userId, postId);
		
		if(post == null) {
			log.warn(" [delete post]post가 없음 postId:{} ");
			return;
		}
		
		fileManagerService.deleteFile(post.getImagePath());
		
		postRepository.deleteByUserIdAndId(userId, postId);
		
		commentBO.deleteCommentByPostId(postId);
		
		likeBO.deleteLikeByPostId(postId);
	}
	
	
	
	
}
