package com.encore.board.Dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AuthorDetailResDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdTime; ;

}
