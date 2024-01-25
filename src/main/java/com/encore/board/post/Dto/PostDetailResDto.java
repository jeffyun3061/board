package com.encore.board.post.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDetailResDto {
    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdTime;
}