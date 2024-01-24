package com.encore.board.author.post.Dto;

import lombok.Data;

@Data
public class PostSaveReqDto {
    private String title;
    private String contents;
}