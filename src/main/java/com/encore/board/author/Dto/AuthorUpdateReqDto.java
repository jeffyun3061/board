package com.encore.board.author.Dto;

import lombok.Data;

@Data
public class AuthorUpdateReqDto {
    private Long id;
    private String name;
    private String email;
    private String password;
}