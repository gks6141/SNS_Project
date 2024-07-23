package com.sns.like.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Like {
	private int userId;
	private int postId;
	private LocalDateTime createdAt;
}
