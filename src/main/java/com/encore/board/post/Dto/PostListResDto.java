package com.encore.board.author.post.Dto;

import lombok.Data;

@Data
public class PostListResDto {
    private Long id;
    private String title;
    private String author_email;
}