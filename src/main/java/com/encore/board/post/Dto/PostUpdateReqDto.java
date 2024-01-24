package com.encore.board.author.post.Dto;

import lombok.Data;

@Data
public class PostUpdateReqDto {
    private String title;
    private String contents;
}