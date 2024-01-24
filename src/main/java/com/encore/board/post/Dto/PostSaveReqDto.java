package com.encore.board.post.Dto;

import lombok.Data;

@Data
public class PostSaveReqDto {
    private String title;
    private String email;
    private String contents;
}